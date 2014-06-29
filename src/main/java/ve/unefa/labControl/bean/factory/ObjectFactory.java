package ve.unefa.labControl.bean.factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

public class ObjectFactory {

    private BeanFactory factory;
    private static ObjectFactory instance = new ObjectFactory();

    private ObjectFactory() {

        BeanFactoryLocator locator = ContextSingletonBeanFactoryLocator.getInstance("classpath*:beanRefContext.xml");
        BeanFactoryReference reference = locator.useBeanFactory("labControlApplicationBeanFactory");
        factory = reference.getFactory();

    }

    public Object getObjeto(String nombre) {
        return (factory.getBean(nombre));
    }

    public static ObjectFactory getInstancia() {
        return (instance);
    }

}