package cdi.demo;

import cdi.demo.annotations.DiyGET;
import cdi.demo.annotations.DiyInject;
import cdi.demo.annotations.DiyPath;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class gives a brief demonstration of the possible Java Code behind the mysterious workings of JAX-RS and CDI.
 * And by doing so, hopefully clarifies some of the magic that junior Software Engineers might experience.
 * <p>
 * This Class does the following things:
 * <ul>
 *     <li>It scans all Classes in Package {@code nl.han.ica.oose.dea} for the Annotation {@link DiyPath}</li>
 *     <li>It creates instances of all found Classes</li>
 *     <li>It scans all Instances for methods that are annotated with {@link DiyInject}</li>
 *     <li>It determines the type of the parameter of the method, annotated with {@link DiyInject}</li>
 *     <li>It creates a new instance with the same type as that parameter</li>
 *     <li>It calls the method annotated with {@link DiyInject} with that newly created instance</li>
 *     <li>It scans all Instances for methods that are annotated with {@link DiyGET}</li>
 *     <li>It calls all methods annotated with {@link DiyGET}</li>
 * </ul>
 * <p>
 * Notice how this code is rather limited and assumes a lot about the following things:
 * <ul>
 *     <li>Only Setter injection is supported. Constructor en field injection is not</li>
 *     <li>The Constructor of the class annotated with {@link DiyPath} and the dependency must be empty </li>
 * </ul>
 */
public class DiyCDIRunnerApplication
{
    private static final String PACKAGE_TO_SCAN = "cdi.demo";

    Reflections ref = new Reflections(DiyCDIRunnerApplication.PACKAGE_TO_SCAN);

    public static void main(String[] args)
    {
        new DiyCDIRunnerApplication().runDiyCdiDemo();
    }

    private void runDiyCdiDemo()
    {
        try
        {
            var instancesHavingDependencies = createInstancesHavingDependencies();

            for (var instance : instancesHavingDependencies)
            {
                createDependenciesAndInject(instance);
                callDependencyMethod(instance);
            }
        }
        catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }

    // Find all classes that may have dependencies and instantiate those (without dependencies)
    public Set<Object> createInstancesHavingDependencies() throws NoSuchMethodException, IllegalAccessException,
            InstantiationException, InvocationTargetException
    {

        return ref.getTypesAnnotatedWith(DiyPath.class).stream()
                .map(clazz ->
                {
                    try
                    {
                        return (Object) clazz.getConstructor().newInstance();
                    }
                    catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                           NoSuchMethodException ex)
                    {
                        return null;
                    }
                })
                .collect(Collectors.toSet());
    }

    // Find all dependency setter-methods, instantiate the method argument and 'inject' it
    private void createDependenciesAndInject(Object instance) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException
    {
        for (var method : instance.getClass().getMethods())
        {
            if (method.isAnnotationPresent(DiyInject.class))
            {
                var classOfMethodArgument = method.getParameterTypes()[0];

                var implementingClass = new ArrayList<>(ref.getSubTypesOf(classOfMethodArgument)).get(0);
                if (implementingClass != null)
                {
                    var instanceOfDependency = implementingClass.getConstructor().newInstance();
                    method.invoke(instance, instanceOfDependency);
                }
            }
        }
    }

    // Test whether the dependency method can be called
    private void callDependencyMethod(Object instance) throws InvocationTargetException,
            IllegalAccessException
    {
        for (var method : instance.getClass().getMethods())
        {
            if (method.isAnnotationPresent(DiyGET.class))
            {
                var response = method.invoke(instance);
                System.out.printf("Calling method %s(), gives response: %s\n", method.getName(), response);
            }
        }
    }
}
