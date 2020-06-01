package project.mundofii.services;

public interface AbstractService<T, K> {

	public K businessRule (T t);
	
	public default ServiceResult<K> execute(T t){
		
		ServiceValidationResult validationResult = validation(t);
		
		if(validationResult.isOk()) {
			ServiceResult<K> resultado = new ServiceResult<>();
			
			resultado.setObject(businessRule(t));
			
			return resultado;
		}
		
		return null;
		
	}

	public ServiceValidationResult validation(T t);
	
}
