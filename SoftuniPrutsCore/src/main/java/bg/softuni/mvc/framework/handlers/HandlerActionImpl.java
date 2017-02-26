package bg.softuni.mvc.framework.handlers;

import bg.softuni.mvc.framework.annotations.parameters.ModelAttribute;
import bg.softuni.mvc.framework.annotations.parameters.PathVariable;
import bg.softuni.mvc.framework.annotations.parameters.RequestParam;
import bg.softuni.mvc.framework.controller.ControllerActionPair;
import bg.softuni.mvc.framework.interfaces.HandlerAction;
import bg.softuni.mvc.framework.model.Model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.*;

public class HandlerActionImpl implements HandlerAction {

    @Override
    public String executeControllerAction(
            HttpServletRequest request, HttpServletResponse response, ControllerActionPair controllerActionPair)
            throws ReflectiveOperationException, NamingException {
        Class controller = controllerActionPair.getControllerType();
        Method method = controllerActionPair.getActionMethod();
        Parameter[] parameters = method.getParameters();
        Object[] arguments = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object argument = null;
            if (parameter.isAnnotationPresent(PathVariable.class)) {
                argument = this.getPathVarVal(controllerActionPair, parameter);
            }
            if (parameter.isAnnotationPresent(RequestParam.class)) {
                argument = this.getRequestParamVal(request, parameter);
            }
            if (parameter.getType().isAssignableFrom(Model.class)) {
                argument = this.getModelVal(request, parameter);
            }
            if (parameter.isAnnotationPresent(ModelAttribute.class)) {
                argument = this.getBindingModelVal(request, parameter);
            }
            if (parameter.getType().isAssignableFrom(HttpSession.class)) {
                argument = request.getSession();
            }
            if (parameter.getType().isAssignableFrom(Cookie[].class)) {
                argument = request.getCookies();
            }
            if(parameter.getType().isAssignableFrom(HttpServletResponse.class)){
                argument = response;
            }
            arguments[i] = argument;
        }
//        Object controllerInstance = controller.newInstance();
        Context context = new InitialContext();
        String controllerName = controller.getSimpleName();
        Object controllerInstance = context.lookup("java:global/" + controllerName);

        String view = (String) method.invoke(controllerInstance, arguments);
        return view;
    }

    private Object getModelVal(HttpServletRequest request, Parameter parameter) throws NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        Object argument;
        Constructor ctor = parameter.getType().getConstructor(HttpServletRequest.class);
        argument = ctor.newInstance(request);
        return argument;
    }

    private Object getBindingModelVal(HttpServletRequest request, Parameter parameter) throws InstantiationException,
            IllegalAccessException {
        Object argument;
        Class bindingModelType = parameter.getType();
        Object bindingModelInstance = bindingModelType.newInstance();
        Field[] fields = bindingModelType.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            request.getParameter(fieldName);
            String fieldValue = request.getParameter(fieldName);
            if (null != fieldValue) {
                field.set(bindingModelInstance, fieldValue);
            }
        }
        argument = bindingModelInstance;
        return argument;
    }

    private Object getRequestParamVal(HttpServletRequest request, Parameter parameter) throws ReflectiveOperationException {
        Object argument;
        RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
        String requestParamKey = requestParam.value();
        String rawArg = request.getParameter(requestParamKey);
        argument = this.convertArgument(parameter, rawArg);
        return argument;
    }

    private Object getPathVarVal(ControllerActionPair controllerActionPair, Parameter parameter)
            throws ReflectiveOperationException {
        Object argument;PathVariable pathVariable = parameter.getAnnotation(PathVariable.class);
        String pathVarKey = pathVariable.value();
        String rawArg = controllerActionPair.getPathVariable(pathVarKey);
        argument = this.convertArgument(parameter, rawArg);
        return argument;
    }

    private Object convertArgument(Parameter parameter, Object inputVal)
            throws ReflectiveOperationException {
        Class paramType = parameter.getType();
        if (paramType.isPrimitive()) {
            paramType = Array.get(Array.newInstance(paramType, 1), 0).getClass();
        }
        Constructor ctor = paramType.getDeclaredConstructor(String.class);
        ctor.setAccessible(true);
        return ctor.newInstance(inputVal);
    }
}