package labs.three;

public interface Converter<T> {
	
	T parse(String fromString); 
	String format(T fromObject);

}
