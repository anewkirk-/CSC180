package labs.two;

public class ObjectNotFoundException extends Exception {

	private static final long serialVersionUID = 2148117709142601388L;
	private Integer id;
	
	public ObjectNotFoundException(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

}
