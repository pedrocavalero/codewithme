import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PedroTestRunner {

	
	public void runTests(Object obj){
		int errors = 0;
		int passed = 0;
		
		Class<?> clazz = obj.getClass();
		for(Method m: clazz.getMethods()){
			if((m.getName().startsWith("test")) 
					&& m.getParameterCount()==0){
				try{
					m.invoke(obj);
					passed++;
				} catch (InvocationTargetException e) {
					errors++;
				} catch (Exception e){
					
				}
			} else {
				for(Annotation an: m.getAnnotations()){
					if(an.annotationType().isAnnotationPresent(PedroTest.class)){
						try{
							PedroTest p = an.annotationType().getAnnotation(PedroTest.class);
							Class<? extends TestCommand> ct = p.value();
							TestCommand tc = ct.newInstance();
							tc.execute();
							m.invoke(obj);
							passed++;
						} catch (InvocationTargetException e) {
							errors++;
						} catch (Exception e){
							
						}
					}
				}
			}
			
		}
		System.out.println("Passaram: " + passed);
		System.out.println("Não passaram: " + errors);
		
		
	}
}
