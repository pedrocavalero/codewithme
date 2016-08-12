
public class DummyTest {

	public void testOK(){
		System.out.println("TestOK");
	}
	
	public void testNOK(){
		System.out.println("TestNOK");
		throw new RuntimeException();
	}
	
	@LogTest
	public void soRodarComAnotacao(){
		System.out.println("Só rodar com anotação");
	}
}
