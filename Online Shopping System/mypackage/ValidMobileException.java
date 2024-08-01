package mypackage;
import java.util.*;

public class ValidMobileException extends Exception
{
	String m;
	public ValidMobileException(String msg)
	{
		super(msg);
		m=msg;
	}
	public String toString()
	{
		return m;
	}
}
