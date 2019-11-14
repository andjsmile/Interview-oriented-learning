
/**
 * 双亲委派模型的类加载机制
 * ClassLoader
 */
public abstract class ClassLoader{

    private final ClassLoader parent;
    
    public Class<?> loadClass(String name) throws ClassNotFoundException{
        return loadClass(name,false);
    }

    protected Class<?> loadClass(String name,boolean resolve) throws ClassNotFoundException{
        synchronized(getClassLoadingLock(name)){
            Class<?>c=findLoadClass(name);
            if (c == null) {
                try {
                    if (parent != null) {
                        c = parent.loadClass(name, false);
                    } else {
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
}