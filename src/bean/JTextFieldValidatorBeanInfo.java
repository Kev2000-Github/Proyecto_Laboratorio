package bean;
import java.beans.*;

public class JTextFieldValidatorBeanInfo  extends SimpleBeanInfo {
	
	  private Class<JTextFieldValidator> beanClass = JTextFieldValidator.class;
	  private String iconColor16x16Filename;
	  private String iconColor32x32Filename= "JTextFieldValidator.gif";
	  private String iconMono16x16Filename;  
	  private String iconMono32x32Filename;

	  
	  public JTextFieldValidatorBeanInfo() {
		  super();
	  }
	  
	  public PropertyDescriptor[] getPropertyDescriptors() {
		    try  {
		      PropertyDescriptor _maximaLongitud = new PropertyDescriptor("maximaLongitud", beanClass, "getMaximaLongitud", "setMaximaLongitud");
		      
		      PropertyDescriptor _tipoCaracteresPermitidos = new PropertyDescriptor("tipoCaracteresPermitidos", beanClass, "getTipoCaracteresPermitidos", "setTipoCaracteresPermitidos");
		           
		      PropertyDescriptor[] pds = new PropertyDescriptor[] {
		        _maximaLongitud,
		        _tipoCaracteresPermitidos,
		      };
		      return pds;
		    }
		    catch (IntrospectionException ex) {
		      ex.printStackTrace();
		      return null;
		    }
		  }

	  
	  public java.awt.Image getIcon(int iconKind) {
	    switch (iconKind) {
	    case BeanInfo.ICON_COLOR_16x16:
	      return iconColor16x16Filename != null ? loadImage(iconColor16x16Filename) : null;
	    case BeanInfo.ICON_COLOR_32x32:
	      return iconColor32x32Filename != null ? loadImage(iconColor32x32Filename) : null;
	    case BeanInfo.ICON_MONO_16x16:
	      return iconMono16x16Filename != null ? loadImage(iconMono16x16Filename) : null;
	    case BeanInfo.ICON_MONO_32x32:
	      return iconMono32x32Filename != null ? loadImage(iconMono32x32Filename) : null;
	    }
	    return null;
	  }
	}

