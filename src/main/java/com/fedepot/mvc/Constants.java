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


package com.fedepot.mvc;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Razor global constants
 *
 * @author Touchumind
 * @since 0.0.1
 */
public interface Constants {

    // Package
    String PACKAGE_NAME = "Razor";

    // System

    /**
     * Root folder, for running a packaged jar file, it will be the folder contain this jar; for source code running, it will be the target folder contains compiled classes folder.
     */
    String ROOT_DIR = new File(Constants.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getAbsolutePath();

    String RAZOR_CLASS_PATH = new File(Constants.class.getResource("/razor.png").getPath()).getParent();
    String APP_CLASS_PATH = Constants.class.getResource("/") == null ? new File(Constants.class.getResource("/razor.png").getPath()).getParent() : new File((Constants.class.getResource("/")).getPath()).getAbsolutePath();

    // Server default constants
    String DEFAULT_SERVER_HOST = "0.0.0.0";

    Integer DEFAULT_SERVER_PORT = 8088;

    String DEFAULT_WEB_ROOT_FOLDER = "WWW"; // relative to classpath

    String DEFAULT_TEMPLATE_ROOT_FOLDER = "WEB-INF/templates"; // relative to classpath

    List<String> DEFAULT_STATICS = Arrays.asList("/favicon.ico", "/robots.txt", "/sitemap.xml", ".txt", ".png", ".jpg", ".jpeg", ".gif", ".bmp", ".tif", ".webp", ".js", ".css", ".json", ".otf", ".eot", ".svg", ".ttf", ".woff", ".woff2");

    List<String> DEFAULT_INDEX_FILES = Arrays.asList("index.html", "index.htm");

    Boolean DEFAULT_SSL_ENABLE = false;

    Boolean DEFAULT_GZIP_ENABLE = false;

    // Http constants
    String DEFAULT_CHARSET = "utf-8";

    Integer DEFAULT_HTTP_CACHE_SECONDS = 3600;

    String CONTENT_TYPE_HTML = "text/html; charset=" + DEFAULT_CHARSET;

    String CONTENT_TYPE_JSON = "application/json; charset=" + DEFAULT_CHARSET;

    String CONTENT_TYPE_TEXT = "text/plain; charset=" + DEFAULT_CHARSET;

    String DEFAULT_SESSION_KEY = "SESSION";

    Integer DEFAULT_SESSION_TIMEOUT = 3600;

    // Env keys
    String ENV_KEY_SERVER_HOST = "razor.server.host";

    String ENV_KEY_SERVER_PORT = "razor.server.port";

    String ENV_KEY_CHARSET = "razor.server.charset";

    String ENV_KEY_SSL = "razor.server.ssl";

    String ENV_KEY_GZIP = "razor.server.gzip";

    String ENV_KEY_WEB_ROOT_FOLDER = "razor.web.root";

    String ENV_KEY_USE_OUTER_WEB_ROOT = "razor.web.root.outer"; // false means use inner resources directory, which is a subfolder of the classpath

    String ENV_KEY_TEMPLATE_ROOT_FOLDER = "razor.web.template.root";

    String ENV_KEY_STATIC_RULES = "razor.web.statics";

    String ENV_KEY_INDEX_FILES = "razor.web.indexs";

    String ENV_KEY_HTTP_CACHE_SECONDS = "razor.web.http.cache.seconds";

    String ENV_KEY_403_PAGE_TEMPLATE = "razor.web.template.403";

    String ENV_KEY_404_PAGE_TEMPLATE = "razor.web.template.404";

    String ENV_KEY_500_PAGE_TEMPLATE = "razor.web.template.500";

    String ENV_KEY_502_PAGE_TEMPLATE = "razor.web.template.502";

    String ENV_KEY_SESSION_KEY = "razor.web.http.session.key";

    String ENV_KEY_SESSION_TIMEOUT = "razor.web.http.session.timeout";


    // Env keys for runtime
    String ENV_RT_KEY_403_HTML = "razor.runtime.web.html.403";

    String ENV_RT_KEY_404_HTML = "razor.runtime.web.html.404";

    String ENV_RT_KEY_500_HTML = "razor.runtime.web.html.500";

    String ENV_RT_KEY_502_HTML = "razor.runtime.web.html.502";

    String ENV_RT_KEY_WEB_ROOT_ABS_PATH = "razor.runtime.web.root.path";

    String ENV_RT_KEY_TEMPLATE_ROOT_ABS_PATH = "razor.runtime.web.template.root.path";
}
