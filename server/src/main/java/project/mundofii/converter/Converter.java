package project.mundofii.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface Converter<T1, T2> {

	default List<T2> convertToDTOList(Iterator<T1> t1){
		
		List<T2> t2l = new ArrayList<T2>();
		
		while(t1.hasNext()) {
			
			t2l.add(convertToDTO(t1.next()));
			
		}
		
		return t2l;
		
	}
	
	public T2 convertToDTO(T1 t1);
	
	public T1 convertToReal(T2 t2);
	
}
