/**
 * Copyright (c) 2017, Touchumind<chinash2010@gmail.com>
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


package com.razor.mvc.route;

import com.razor.exception.RazorException;
import com.razor.mvc.annotation.FromBody;
import com.razor.mvc.http.ContentType;
import com.razor.mvc.http.Request;
import com.razor.mvc.http.Response;

import com.razor.mvc.json.GsonFactory;
import io.netty.util.CharsetUtil;
import lombok.Builder;
import lombok.Getter;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.razor.mvc.http.HttpHeaderNames.*;

/**
 * Signature for route matching of one request
 *
 * @author Touchumind
 * @since 0.0.1
 */
@Builder
public class RouteSignature {

    @Getter
    private Router router;

    private Method action;

    /**
     * parameters are these applied to the action, including 0 or multi parameters from url
     */
    @Getter
    private Object[] parameters;

    private Request request;

    private Response response;

    public Request request() {

        return request;
    }

    public Response response() {

        return response;
    }

    public void setRouter(Router router) throws RazorException {

        this.router = router;
        this.action = router.getAction();

        if (action != null) {

            this.initParams();
        }
    }

    private void initParams() throws RazorException {

        if (request == null) {

            throw new RazorException("Empty request error");
        }

        if (action == null) {

            throw new RazorException("Null route action error");
        }

        int actionParamCount = action.getParameterCount();
        if (actionParamCount == 0) {

            parameters = new Object[0];

            return;
        }

        RouteParameter[] routeParams = request.pathParams();
        Parameter[] actionParams = action.getParameters();
        Object[] paramValues = new Object[actionParamCount];

        int i = 0;
        for (Parameter parameter : actionParams) {

            if (i < routeParams.length) {

                paramValues[i] = routeParams[i].getValue();
            } else if (parameter.getAnnotation(FromBody.class) != null) {

                if (request.get(CONTENT_TYPE).toLowerCase().equals(ContentType.JSON.getMimeType())) {

                    String rawBody = request.getRawBody().toString(CharsetUtil.UTF_8);
                    Object value = GsonFactory.getGson().fromJson(rawBody, parameter.getType());

                    paramValues[i] = value;
                    request.setBody(value);
                } else {

                    if (request.formParams() != null) {
                        Map<String, List<String>> formParams = request.formParams();
                        Map<String, Object> formatFormParams = new HashMap<>();

                        for (String key : formParams.keySet()) {

                            List<String> valueList = formParams.get(key);
                            if (valueList != null && valueList.size() == 1) {

                                formatFormParams.put(key, valueList.get(0));
                            } else {

                                formatFormParams.put(key, valueList);
                            }
                        }

                        String json = GsonFactory.getGson().toJson(formatFormParams);
                        Object value = GsonFactory.getGson().fromJson(json, parameter.getType());
                        paramValues[i] = value;
                    } else {

                        paramValues[i] = null;
                    }
                }
            } else {

                paramValues[i] = null;
            }

            i++;
        }

        parameters = paramValues;
    }
}
