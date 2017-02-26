package bg.softuni.mvc.framework.handlers;

import bg.softuni.mvc.framework.annotations.controller.Controller;
import bg.softuni.mvc.framework.annotations.request.GetMapping;
import bg.softuni.mvc.framework.annotations.request.PostMapping;
import bg.softuni.mvc.framework.controller.ControllerActionPair;
import bg.softuni.mvc.framework.interfaces.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class HandlerMappingImpl implements HandlerMapping {

    @Override
    public ControllerActionPair findController(HttpServletRequest request)
            throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ControllerActionPair actionPair = null;
        String urlPath = request.getRequestURI();
        String projectPath = URLDecoder.decode(request.getServletContext()
                .getResource("/WEB-INF/classes").getPath(), "UTF-8");
        List<Class> controllers = this.findAllControllers(projectPath);
        for (Class controller : controllers) {
            Method[] methods = controller.getDeclaredMethods();
            for (Method method : methods) {
                String methodPath = this.findMethodPath(request, method);
                if (methodPath == null) {
                    continue;
                }
                if (this.isPathMatching(urlPath, methodPath)) {
                    actionPair = new ControllerActionPair(controller, method);
                    this.addPathVariables(actionPair, urlPath, methodPath);
                    return actionPair;
                }
            }
        }
        return null;
    }

    private void addPathVariables(ControllerActionPair controllerActionPair, String urlPath, String methodPath) {
        String[] uriTokens = urlPath.split("/");
        String[] methodTokens = methodPath.split("/");
        for (int i = 0; i < methodTokens.length; i++) {
            String methodToken = methodTokens[i];
            String uriToken = uriTokens[i];
            if (!(methodToken.startsWith("{") && methodToken.endsWith("}"))) {
                continue;
            }
            String key = methodToken.substring(1, methodToken.length() - 1);
            String value = uriToken;
            controllerActionPair.addPathVariable(key, value);
        }
    }


    private boolean isPathMatching(String urlPath, String methodPath) {
//        if (urlPath.endsWith("/") && !methodPath.endsWith("/")) {
//            return false;
//        }
        boolean isPathMatching = true;
        String[] uriTokens = urlPath.split("/");
        String[] methodTokens = methodPath.split("/");
        if (uriTokens.length != methodTokens.length) {
            isPathMatching = false;
            return isPathMatching;
        }
        for (int i = 0; i < uriTokens.length; i++) {
            String uriToken = uriTokens[i];
            String methodToken = methodTokens[i];
            if (methodToken.startsWith("{") && methodToken.endsWith("}")) {
                continue;
            }
            if  (!uriToken.equals(methodToken)) {
                isPathMatching = false;
                break;
            }
        }
        return isPathMatching;
    }

    private String findMethodPath(HttpServletRequest request, Method method)
            throws IllegalAccessException, InstantiationException {
        String path = null;
        String methodType = request.getMethod().toUpperCase();
        if ("GET".equals(methodType)) {
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            if (null != getMapping) {
                path = getMapping.value();
            }
        } else if ("POST".equals(methodType)) {
            PostMapping postMapping = method.getAnnotation(PostMapping.class);
            if (null != postMapping) {
                path = postMapping.value();
            }
        }
        return path;
    }

    private List<Class> findAllControllers(String projectDirectory) throws ClassNotFoundException, IOException {
        List<Class> controllerClasses = new ArrayList<>();
        File directory = new File(projectDirectory);
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                Class controllerWannaBe = this.getClass(file);
                if (controllerWannaBe == null) {
                    continue;
                }
                if (!controllerWannaBe.isAnnotationPresent(Controller.class)) {
                    continue;
                }
                controllerClasses.add(controllerWannaBe);
            } else if (file.isDirectory()) {
                String subDir = file.getAbsolutePath();
                controllerClasses.addAll(this.findAllControllers(subDir));
            }
        }
        return controllerClasses;
    }

    private Class getClass(File file) throws ClassNotFoundException {
        String absolutePath = file.getAbsolutePath();
        if (!absolutePath.endsWith(".class")) {
            return null;
        }
        int beginIndex = absolutePath.indexOf("classes\\") + "classes\\".length();
        int endIndex = absolutePath.length() - ".class".length();
        String className = absolutePath.substring(beginIndex, endIndex);
        className = className.replace('\\', '.');
        Class currentClass = null;
        if (!className.endsWith("DispatcherServlet")) {
            currentClass = Class.forName(className);
        }
        return currentClass;
    }
}