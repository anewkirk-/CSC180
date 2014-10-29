package labs.three;

public class ObjectNotFoundException extends Exception {

	private static final long serialVersionUID = 2148117709142601388L;
	private Long id;
	
	public ObjectNotFoundException(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}

}
