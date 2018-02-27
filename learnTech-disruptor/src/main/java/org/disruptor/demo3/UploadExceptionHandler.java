package org.disruptor.demo3;



import com.lmax.disruptor.ExceptionHandler;



/**
 * ClassName: UploadExceptionHandler  
 * 自定义异常处理
 * @author DOUBLE
 * @version
 */
@SuppressWarnings("rawtypes")
public class UploadExceptionHandler implements ExceptionHandler {
    

    @Override
    public void handleOnStartException(Throwable ex) {
    	System.out.println("handleOnStartException" + ex);
    }

    @Override
    public void handleOnShutdownException(Throwable ex) {
    	System.out.println("handleOnShutdownException" + ex);
    }

	@Override
	public void handleEventException(Throwable ex, long sequence, Object event) {
		if(ex instanceof RuntimeException) {
    		System.out.println("handleEventException RuntimeException" + ex);
    	} else {
    		System.out.println(ex);
    	}
		throw new RuntimeException("123");
	}
}
