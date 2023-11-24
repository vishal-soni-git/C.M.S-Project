import java.util.*;
//made by vishal soni

public class GenerateOtp
{
	char[] otpGenerator(int l)
	{
		System.out.println("your OTP is");
		String s="1234567890";
		Random r=new Random();
		char q[]=new char[l];
		for(int i=0;i<l;i++)
		{
			q[i]=s.charAt(r.nextInt(s.length()));
		}
		return q;
	}
}