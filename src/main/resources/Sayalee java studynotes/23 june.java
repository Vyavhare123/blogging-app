if else statement:

package njakl;

public class judksn {

	public static void main(String[] args) {
	int	 x = 11;
	int  y = 8;
	
	if(x>y)
	System.out.println(x);
	else
	System.out.println(y);
	
	
	
	}

}

output:11
8



if you had a singe statement you dont need {}.if you have to print more than one statement then we use {}.
package njakl;

public class judksn {

	public static void main(String[] args) {
	int	 x = 11;
	int  y = 8;
	
	
	if(x>y)
	{
		System.out.println(x);
	
	System.out.println("Thank you");
	}
	
	else
	System.out.println(y);
	
	
	
	}

}

output: 11
        Thank you                1.59.24
		
		
		
		
		when more than two int are present:
		
		package njakl;

public class judksn {

	public static void main(String[] args) {
	int	 x = 11;
	int  y = 8;
	int z = 9;
	
	
	if(x>y  && x>z)
	
		System.out.println(x);
	
	
	else
	System.out.println(y);
	
	
	
	}

}
 
 output: 11
 
 
 package njakl;

public class judksn {

	public static void main(String[] args) {
	int	 x = 11;
	int  y = 8;
	int z = 9;
	
	
	if(x>y  && x>z)
	
		System.out.println(x);
	
	else if (y>x  && y>z)
	System.out.println(y);
	
	
	
	}

}

output:11

when we have to give a condition in else statemnt then we use another if in that statement.

package njakl;

public class judksn {

	public static void main(String[] args) {
	int	 x = 7;
	int  y = 8;
	int z = 9;
	
	
	if(x>y  && x>z)
	
		System.out.println(x);
	
	else if (y>x  && y>z)
	System.out.println(y);
	 
	else 
		System.out.println(z);
	
	
	
	}

}

output:9      here we already use if in else condition and our both output are false so there nothing will print .so, for output we add another else condition we get result.try to remove unnecessary conditiom it will reduce your time and efforts as well.



Ternary Operator:

package njakl;

public class judksn {

	public static void main(String[] args) {
	
	int  y = 8;
	int result = 0;
	
	if (y%2==0)
		result = 10;
	else 
		result = 20;
	
	System.out.println(result);
	
	
	}

}

output: 10



package njakl;

public class judksn {

	public static void main(String[] args) {
	
	int  y = 9;
	int result = 0;
	
	if (y%2==0)
		result = 10;
	else 
		result = 20;
	
	System.out.println(result);
	
	
	}

}

output: 20
 
		Now we are going to use ternary operator. "?:". if the output is true then we can take ? later statement ? behaves like if  and :behaves like else.
		
		
		package njakl;

public class judksn {

	public static void main(String[] args) {
	
	int  y = 9;
	int result = 0;
	
	result = y%2==0 ? 10 : 20 ;
	
	System.out.println(result);
	
	}

}
output:20  This is not suitable for all type of example.it is just shortcut for some example.

Switch Statement:
