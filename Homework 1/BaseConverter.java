// Share with rriver17@lion.lmu.edu

/** This program converts a given number from one base to another:
    <ul>
        <li>args[0] represents the starting number;</li>
        <li>args[1] represents the starting base;</li>
        <li>args[2] represents the target base.</li>
    </ul>

<b>Conversions to or from unity (base one) are not permitted. [Added 2015-09-19T17:13:00]</b></p>

<p>We require two or three arguments: the starting number will be specified using a special notation (below);
the starting and target bases must both be positive and will be given in decimal. The target base, if absent, defaults to decimal. 
Note that the "digits" in the starting number must be consistent with the starting base. All arithmetic is done using Java type <i>long</i>, whose 
largest value is <a href="http://docs.oracle.com/javase/7/docs/api/java/lang/Long.html#MAX_VALUE">java.lang.Long.MAX_VALUE</a>.</p>

<p>In order to represent numbers from all possible bases, we employ a special notation:
a string like [d1][d2]...[dk] represents a k-digit number whose high-order digit has
value d1 (decimal), whose next digit has value d2 (decimal), etc. Here are some examples:
    <ul>
        <li>The number 10110 would be represented [1][0][1][1][0].
        <li>The number 3204124 would be represented [3][2][0][4][1][2][4].
        <li>The base three number that means forty-seven would be represented [1][2][0][2].
        <li>The hexadecimal number for which we ordinarily write B3C would be represented [11][3][12].
    </ul></p>
 
<p>So, to convert from 314 base five to base three, we would run <i>java BaseConverter [3][1][4] 5 3</i>.
To convert BBA326CF from hexadecimal to base twenty, we would run <i>java BaseConverter [11][11][10][3][2][6][12][15] 16 20</i>. 
If we run <i>java BaseConverter [6][14] 26 14</i>, the output should be <i>[12][2]</i>.</p>

<p>The above-described special notation should also be used for outputting the target number.</p>


*/

import java.util.Arrays;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Stack;

public class BaseConverter {

	private long startingBase;
	private long endBase;
    private long[] values;
    private long numberOfDigits;
    private static final long BASE_TEN = 10;

    /** This method attempts to validate the command-line arguments. If they're
        okay, it returns true; otherwise, it returns false. */

    public BaseConverter(String values, long b1, long b2){
        // this.startingBase = b1;
        this.setStartingBase(b1);
        // this.endBase = b2;
        this.setEndBase(b2);
        // this.values = inputConverter(values);
        this.setValues(inputConverter(values));
    }

    public BaseConverter(String values, long b1){
        // this.startingBase = b1;
        this.setStartingBase(b1);
        // this.endBase = 10;
        this.setEndBase(BASE_TEN);
        // this.values = inputConverter(values);
        this.setValues(inputConverter(values));
    }

    public String run(long[] values, long b2){
        // return returnString(toEndBase(baseTen(this.values, this.startingBase), this.endBase));
        return returnString(toEndBase(baseTen(this.getValues(), this.getStartingBase()), this.getEndBase()));

    }
    
    public static boolean validArgs ( String[] args ) {
        // YOUR CODE GOES HERE
        if (args.length < 2 || args.length > 3){
            return false;
        }
        if(args.length > 2){
            try {
                Long.parseLong(args[2]);
            }catch (NumberFormatException e){
                return false;
            }
        }
        try {
            Long.parseLong(args[1]);
        }catch (NumberFormatException e){
            return false;
        }
        if ((Long.parseLong(args[1]) < 2 || (args.length > 2 && Long.parseLong(args[2]) < 2))) {
        	return false;
        }
        if (!(balanceCheck(args[0]))){
            return false;
        }
        return true;
    }
 
    private long[] inputConverter(String s) {
    	// converts args longo list of long values
    	long stiringLength = s.length();
        int numberOfValues = digitCounter(s);
    	long[] valueList = new long[numberOfValues];
    	int currentValueListIndex = 0;
    	long currentCharacter;
    	for (int i = 0; i < stiringLength; i++) {
    		if ((s.charAt(i) != '[') && (s.charAt(i) != ']')){
    			currentCharacter = Character.getNumericValue(s.charAt(i));
    			while (s.charAt(i + 1) != ']') {
    				i++;
    				currentCharacter = shiftLeft(currentCharacter) + Character.getNumericValue(s.charAt(i));
                    if(currentCharacter >= startingBase){
                        throw new IllegalArgumentException("Digit value greater than starting base");
                    }
    			}
    			valueList[currentValueListIndex] = currentCharacter;
    			currentValueListIndex++;
    		}
    	}
    	return valueList;
    }

    private long baseTen(long[] digits, long base) {
        long baseTenValue = 0;
        for(int i = 0; i < digits.length; i++){
            if(i > 0){
                baseTenValue = shiftLeft(baseTenValue, base);
            }
            baseTenValue += digits[i];
        }
        return baseTenValue;
    }

    private ArrayList<Long> toEndBase(long baseTen, long newBase){
        long b10 = baseTen;
        ArrayList<Long> baseXList = new ArrayList<Long>();
        if(b10 == 0){
            baseXList.add(b10);
            return baseXList;
        }
        while(b10 != 0){
            baseXList.add(b10 % newBase);
            b10 = b10 / newBase;            
        }
        baseXList.trimToSize();
        return baseXList;
    }

    private String returnString(ArrayList<Long> resultList){
        StringBuilder result = new StringBuilder();
        for(int i = (resultList.size() - 1); i >= 0; i--){
            result.append('[');
            result.append(resultList.get(i));
            result.append(']');
        }
        return result.toString();
    }

    private long shiftLeft(long x){
    	long result = x * 10;
    	return result;
    }

    private long shiftLeft(long x, long base){
        long result = x * base;
        return result;
    }

    private int digitCounter(String s){
        int leftCounter = 0;
        int rightCounter = 0;
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '['){
                leftCounter++;
            }else if(s.charAt(i) == ']'){
                rightCounter++;
            }
        }
        if(leftCounter != rightCounter){
            throw new IllegalArgumentException("Check your brackets.");
        }else return leftCounter;
    }

    private static boolean balanceCheck(String s){
        Stack<Character> bracketStack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++){
            char current = s.charAt(i);
            if (current == '[') {
                bracketStack.push(current);
            }else if(current == ']'){
                if(bracketStack.isEmpty()){
                    return false;
                }else if (bracketStack.pop() != '['){
                    return false;
                }
            }else if(!(Character.isDigit(current))){
                return false;
            }else if(Character.isDigit(current) && bracketStack.isEmpty()){
                return false;
            }
        }
        if (bracketStack.isEmpty()){
            return true;
        }
        return false;
    }

    private void setStartingBase(long b){
        this.startingBase = b;
    }

    private void setEndBase(long b){
        this.endBase = b;
    }

    private void setValues(long[] v){
        this.values = v;
    }

    private void setNumberOfDigits(long d){
        this.numberOfDigits = d;
    }

    private long getStartingBase(){
        return this.startingBase;
    }

    private long getEndBase(){
        return this.endBase;
    }

    private long[] getValues(){
        return this.values;
    }

    private long getNumberOfDigits(){
        return this.numberOfDigits;
    }  

/** This method calls validArgs() to check the command-line arguments and, if they're valid, 
        it takes care of the conversion and outputs the result. */
    
    public static void main ( String[] args ) {
        if ( ! validArgs ( args ) ) {
            throw new IllegalArgumentException();
        }
        else {
            // YOUR CODE GOES HERE
            if (args.length == 2){
                BaseConverter baseConverter = new BaseConverter(args[0], Long.parseLong(args[1]));
                // System.out.println(baseConverter.run(baseConverter.values, baseConverter.endBase));
                System.out.println(baseConverter.run(baseConverter.getValues(), baseConverter.getEndBase()));
            }else if (args.length == 3){
                BaseConverter baseConverter = new BaseConverter(args[0], Long.parseLong(args[1]), Long.parseLong(args[2]));
                // System.out.println(baseConverter.run(baseConverter.values, baseConverter.endBase));
                System.out.println(baseConverter.run(baseConverter.getValues(), baseConverter.getEndBase()));
            }else throw new IllegalArgumentException("Too many inputs");
            
        }
    }
}