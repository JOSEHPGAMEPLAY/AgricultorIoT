package iser.apiOrion.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Component
@ComponentScan(basePackages = "iser.apiOrion")
public class CorsConfigConstants {

    /**
     * Environment
     */
    private final Environment environment;

    /**
     * Constants for CORS
     * ALLOWED_PATH (/**)
     */
    public static final String ALLOWED_PATH = "/**";

    /**
     * Constants for CORS
     * METHOD_GET (GET)
     */
    public static final String METHOD_GET = "GET";

    /**
     * Constants for CORS
     * METHOD_POST (POST)
     */
    public static final String METHOD_POST = "POST";

    /**
     * Constants for CORS
     * METHOD_PUT (PUT)
     */
    public static final String METHOD_PUT = "PUT";

    /**
     * Constants for CORS
     * METHOD_DELETE (DELETE)
     */
    public static final String METHOD_DELETE = "DELETE";

    /**
     * Constants for CORS
     * ALLOWED_METHODS (GET, POST, PUT, DELETE)
     */
    public static final List<String> ALLOWED_METHODS = Arrays.asList("GET", "POST", "PUT", "DELETE");

    /**
     * Constants for CORS
     * ALLOWED_HEADERS (Authorization, Content-Type)
     */
    public static final List<String> ALLOWED_HEADERS = Arrays.asList("Authorization", "Content-Type");

    /**
     * Constants for CORS
     * MAX_AGE (3600)
     */
    public static final long MAX_AGE = 3600L;

    /**
     * Constants for CORS
     * ALLOWED_ORIGINS (ALLOWED_ORIGINS)
     */
    private static String[] ALLOWED_ORIGINS;

    /**
     * Constants for CORS
     * ALLOW_CREDENTIALS (Boolean)
     */
    public static boolean ALLOW_CREDENTIALS;

    /**
     * CorsConfigConstants constructor with Environment parameter
     * <p>
     *     This constructor is used to initialize the environment variable
     *     and set the values of the constants
     *     ALLOWED_ORIGINS and ALLOW_CREDENTIALS
     *     with the values of the properties
     *     edu.siscertup.cors.allowed-origins and edu.siscertup.cors.allow-credentials
     *     respectively
     * </p>
     * @param environment An Environment parameter
     */
    @Autowired
    public CorsConfigConstants(Environment environment) {
        this.environment = environment;
        ALLOWED_ORIGINS = new String[]{environment.getProperty("edu.cors.allowed-origins")};
        ALLOW_CREDENTIALS = Boolean.parseBoolean(environment.getProperty("edu.cors.allow-credentials"));
    }

    /**
     * getCreateListParameter method with String[] parameter
     * <p>
     *     This method is used to create a list of strings from a String[] parameter
     *     and return the list
     * </p>
     * @param parameter A String[] parameter
     * @return A list of strings
     */
    public List<String> getCreateListParameter(String[] parameter) {
        return Arrays.asList(parameter);
    }

    /**
     * getAllowCredentials method without parameters and boolean return
     * <p>
     *     This method is used to return the value of the constant ALLOW_CREDENTIALS
     *     that is a boolean value (true or false)
     *     and return the boolean value
     * </p>
     * @return A boolean value
     */
    public List<String> getAllowedOrigins() {
        return getCreateListParameter(ALLOWED_ORIGINS);
    }

    /**
     * getAllCorsConfiguration method without parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the constants
     *     ALLOWED_ORIGINS, ALLOWED_METHODS, ALLOWED_HEADERS and ALLOW_CREDENTIALS
     *     and return the CorsConfiguration object
     * </p>
     * @return A CorsConfiguration object
     */
    public CorsConfiguration getAllCorsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(getAllowedOrigins());
        config.setAllowedMethods(ALLOWED_METHODS);
        config.setAllowedHeaders(ALLOWED_HEADERS);
        config.setAllowCredentials(ALLOW_CREDENTIALS);
        return config;
    }

    /**
     * getCorsConfiguration method with String[] origins, String[] headers, String[] methods and boolean credentials parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the parameters
     *     origins, headers, methods and credentials
     *     and return the CorsConfiguration object
     * </p>
     * @param origins A String[] parameter Ej:(http://localhost:8080)
     * @param headers A String[] parameter Ej:(Authorization, Content-Type)
     * @param methods A String[] parameter Ej:(GET, POST, PUT, DELETE)
     * @param credentials A boolean parameter Ej:(true, false)
     * @return A CorsConfiguration object with the values of the parameters
     */
    public CorsConfiguration getCorsConfiguration(String[] origins, String[] headers, String[] methods, boolean credentials) {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(getCreateListParameter(origins));
        config.setAllowedMethods(getCreateListParameter(methods));
        config.setAllowedHeaders(getCreateListParameter(headers));
        config.setAllowCredentials(credentials);
        return config;
    }

    /**
     * getCorsConfiguration method with String[] origins, String[] headers and String[] methods parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the parameters
     *     origins, headers and methods
     *     and return the CorsConfiguration object
     * </p>
     * @param origins A String[] parameter Ej:(http://localhost:8080)
     * @param headers A String[] parameter Ej:(Authorization, Content-Type)
     * @param methods A String[] parameter Ej:(GET, POST, PUT, DELETE)
     * @return A CorsConfiguration object with the values of the parameters
     */
    public CorsConfiguration getCorsConfiguration(String[] origins, String[] headers, String[] methods) {
        return getCorsConfiguration(origins, headers, methods, ALLOW_CREDENTIALS);
    }

    /**
     * getCorsConfiguration method with String[] origins and String[] methods parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the parameters origins and methods
     *     and return the CorsConfiguration object
     * </p>
     * @param origins A String[] parameter Ej: (http://localhost:8080)
     * @param methods A String[] parameter Ej: (GET, POST, PUT, DELETE)
     * @return A CorsConfiguration object with the values of the parameters
     */
    public CorsConfiguration getCorsConfiguration(String[] origins, String[] methods) {
        return getCorsConfiguration(origins, ALLOWED_HEADERS.toArray(new String[ALLOWED_HEADERS.size()]), methods, ALLOW_CREDENTIALS);
    }

    /**
     * getCorsConfiguration method with String[] origins parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the parameter origins
     *     and return the CorsConfiguration object
     * </p>
     * @param origins A String[] parameter Ej: (http://localhost:8080)
     * @return A CorsConfiguration object with the values of the parameter
     */
    public CorsConfiguration getCorsConfiguration(String[] origins) {
        return getCorsConfiguration(origins, ALLOWED_METHODS.toArray(new String[ALLOWED_METHODS.size()]), ALLOWED_HEADERS.toArray(new String[ALLOWED_HEADERS.size()]), ALLOW_CREDENTIALS);
    }

    /**
     * getCorsConfiguration method without parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the constants
     *     ALLOWED_ORIGINS, ALLOWED_METHODS, ALLOWED_HEADERS and ALLOW_CREDENTIALS
     *     and return the CorsConfiguration object
     * </p>
     * @return A CorsConfiguration object with the values of the constants
     */
    public CorsConfiguration getCorsConfiguration() {
        return getCorsConfiguration(ALLOWED_ORIGINS, ALLOWED_METHODS.toArray(new String[ALLOWED_METHODS.size()]), ALLOWED_HEADERS.toArray(new String[ALLOWED_HEADERS.size()]), ALLOW_CREDENTIALS);
    }

    /**
     * getGetCorsConfiguration method with String[] origins, String[] headers and boolean credentials parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the parameters
     *     origins, headers and credentials
     *     and return the CorsConfiguration object
     * </p>
     * @param origins A String[] parameter Ej:(http://localhost:8080)
     * @param headers A String[] parameter Ej:(Authorization, Content-Type)
     * @param credentials A boolean parameter Ej:(true, false)
     * @return A CorsConfiguration object with the values of the parameters
     */
    public CorsConfiguration getGetCorsConfiguration(String[] origins, String[] headers, boolean credentials) {
        return getCorsConfiguration(origins, headers, new String[] {METHOD_GET}, credentials);
    }

    /**
     * getGetCorsConfiguration method with String[] origins, String[] headers parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the parameters
     *     origins and headers
     *     and return the CorsConfiguration object
     * </p>
     * @param origins A String[] parameter Ej:(http://localhost:8080)
     * @param headers A String[] parameter Ej:(Authorization, Content-Type)
     * @return A CorsConfiguration object with the values of the parameters
     */
    public CorsConfiguration getGetCorsConfiguration(String[] origins, String[] headers) {
        return getGetCorsConfiguration(origins, headers, ALLOW_CREDENTIALS);
    }

    /**
     * getGetCorsConfiguration method with String[] origins parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the parameter origins
     *     and return the CorsConfiguration object
     * </p>
     * @param origins A String[] parameter Ej:(http://localhost:8080)
     * @return A CorsConfiguration object with the values of the parameter
     */
    public CorsConfiguration getGetCorsConfiguration(String[] origins) {
        return getGetCorsConfiguration(origins, ALLOWED_HEADERS.toArray(new String[ALLOWED_HEADERS.size()]), ALLOW_CREDENTIALS);
    }

    /**
     * getGetCorsConfiguration method without parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the constants
     *     ALLOWED_ORIGINS, ALLOWED_HEADERS and ALLOW_CREDENTIALS
     *     and return the CorsConfiguration object
     * </p>
     * @return A CorsConfiguration object with the values of the constants
     */
    public CorsConfiguration getGetCorsConfiguration() {
        return getGetCorsConfiguration(ALLOWED_ORIGINS, ALLOWED_HEADERS.toArray(new String[ALLOWED_HEADERS.size()]), ALLOW_CREDENTIALS);
    }

    /**
     * getPostCorsConfiguration method with String[] origins, String[] headers and boolean credentials parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the parameters
     *     origins, headers and credentials
     *     and return the CorsConfiguration object
     * </p>
     * @param origins A String[] parameter Ej:(http://localhost:8080)
     * @param headers A String[] parameter Ej:(Authorization, Content-Type)
     * @param credentials A boolean parameter Ej:(true, false)
     * @return A CorsConfiguration object with the values of the parameters
     */
    public CorsConfiguration getPostCorsConfiguration(String[] origins, String[] headers, boolean credentials) {
        return getCorsConfiguration(origins, headers, new String[] {METHOD_POST}, credentials);
    }

    /**
     * getPostCorsConfiguration method with String[] origins, String[] headers parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the parameters
     *     origins and headers
     *     and return the CorsConfiguration object
     * </p>
     * @param origins A String[] parameter Ej:(http://localhost:8080)
     * @param headers A String[] parameter Ej:(Authorization, Content-Type)
     * @return A CorsConfiguration object with the values of the parameters
     */
    public CorsConfiguration getPostCorsConfiguration(String[] origins, String[] headers) {
        return getPostCorsConfiguration(origins, headers, ALLOW_CREDENTIALS);
    }

    /**
     * getPostCorsConfiguration method with String[] origins parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the parameter origins
     *     and return the CorsConfiguration object
     * </p>
     * @param origins A String[] parameter Ej:(http://localhost:8080)
     * @return A CorsConfiguration object with the values of the parameter
     */
    public CorsConfiguration getPostCorsConfiguration(String[] origins) {
        return getPostCorsConfiguration(origins, ALLOWED_HEADERS.toArray(new String[ALLOWED_HEADERS.size()]), ALLOW_CREDENTIALS);
    }

    /**
     * getPostCorsConfiguration method without parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the constants
     *     ALLOWED_ORIGINS, ALLOWED_HEADERS and ALLOW_CREDENTIALS
     *     and return the CorsConfiguration object
     * </p>
     * @return A CorsConfiguration object with the values of the constants
     */
    public CorsConfiguration getPostCorsConfiguration() {
        return getPostCorsConfiguration(ALLOWED_ORIGINS, ALLOWED_HEADERS.toArray(new String[ALLOWED_HEADERS.size()]), ALLOW_CREDENTIALS);
    }

    /**
     * getPutCorsConfiguration method with String[] origins, String[] headers and boolean credentials parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the parameters
     *     origins, headers and credentials
     *     and return the CorsConfiguration object
     * </p>
     * @param origins A String[] parameter Ej:(http://localhost:8080)
     * @param headers A String[] parameter Ej:(Authorization, Content-Type)
     * @param credentials A boolean parameter Ej:(true, false)
     * @return A CorsConfiguration object with the values of the parameters
     */
    public CorsConfiguration getPutCorsConfiguration(String[] origins, String[] headers, boolean credentials) {
        return getCorsConfiguration(origins, headers, new String[] {METHOD_PUT}, credentials);
    }

    /**
     * getPutCorsConfiguration method with String[] origins, String[] headers parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the parameters
     *     origins and headers
     *     and return the CorsConfiguration object
     * </p>
     * @param origins A String[] parameter Ej:(http://localhost:8080)
     * @param headers A String[] parameter Ej:(Authorization, Content-Type)
     * @return A CorsConfiguration object with the values of the parameters
     */
    public CorsConfiguration getPutCorsConfiguration(String[] origins, String[] headers) {
        return getPutCorsConfiguration(origins, headers, ALLOW_CREDENTIALS);
    }

    /**
     * getPutCorsConfiguration method with String[] origins parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the parameter origins
     *     and return the CorsConfiguration object
     * </p>
     * @param origins A String[] parameter Ej:(http://localhost:8080)
     * @return A CorsConfiguration object with the values of the parameter
     */
    public CorsConfiguration getPutCorsConfiguration(String[] origins) {
        return getPutCorsConfiguration(origins, ALLOWED_HEADERS.toArray(new String[ALLOWED_HEADERS.size()]), ALLOW_CREDENTIALS);
    }

    /**
     * getPutCorsConfiguration method without parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the constants
     *     ALLOWED_ORIGINS, ALLOWED_HEADERS and ALLOW_CREDENTIALS
     *     and return the CorsConfiguration object
     * </p>
     * @return A CorsConfiguration object with the values of the constants
     */
    public CorsConfiguration getPutCorsConfiguration() {
        return getPutCorsConfiguration(ALLOWED_ORIGINS, ALLOWED_HEADERS.toArray(new String[ALLOWED_HEADERS.size()]), ALLOW_CREDENTIALS);
    }

    /**
     * getDeleteCorsConfiguration method with String[] origins, String[] headers and boolean credentials parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the parameters
     *     origins, headers and credentials
     *     and return the CorsConfiguration object
     * </p>
     * @param origins A String[] parameter Ej:(http://localhost:8080)
     * @param headers A String[] parameter Ej:(Authorization, Content-Type)
     * @param credentials A boolean parameter Ej:(true, false)
     * @return A CorsConfiguration object with the values of the parameters
     */
    public CorsConfiguration getDeleteCorsConfiguration(String[] origins, String[] headers, boolean credentials) {
        return getCorsConfiguration(origins, headers, new String[] {METHOD_DELETE}, credentials);
    }

    /**
     * getDeleteCorsConfiguration method with String[] origins, String[] headers parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the parameters
     *     origins and headers
     *     and return the CorsConfiguration object
     * </p>
     * @param origins A String[] parameter Ej:(http://localhost:8080)
     * @param headers A String[] parameter Ej:(Authorization, Content-Type)
     * @return A CorsConfiguration object with the values of the parameters
     */
    public CorsConfiguration getDeleteCorsConfiguration(String[] origins, String[] headers) {
        return getDeleteCorsConfiguration(origins, headers, ALLOW_CREDENTIALS);
    }

    /**
     * getDeleteCorsConfiguration method with String[] origins parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the parameter origins
     *     and return the CorsConfiguration object
     * </p>
     * @param origins A String[] parameter Ej:(http://localhost:8080)
     * @return A CorsConfiguration object with the values of the parameter
     */
    public CorsConfiguration getDeleteCorsConfiguration(String[] origins) {
        return getDeleteCorsConfiguration(origins, ALLOWED_HEADERS.toArray(new String[ALLOWED_HEADERS.size()]), ALLOW_CREDENTIALS);
    }

    /**
     * getDeleteCorsConfiguration method without parameters and CorsConfiguration return
     * <p>
     *     This method is used to create a CorsConfiguration object with the values of the constants
     *     ALLOWED_ORIGINS, ALLOWED_HEADERS and ALLOW_CREDENTIALS
     *     and return the CorsConfiguration object
     * </p>
     * @return A CorsConfiguration object with the values of the constants
     */
    public CorsConfiguration getDeleteCorsConfiguration() {
        return getDeleteCorsConfiguration(ALLOWED_ORIGINS, ALLOWED_HEADERS.toArray(new String[ALLOWED_HEADERS.size()]), ALLOW_CREDENTIALS);
    }

}