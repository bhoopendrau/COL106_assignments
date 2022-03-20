

class InvalidPostfixException extends Exception {
        public InvalidPostfixException() {
            super();
        }
}

class InvalidExprException extends Exception {
    public InvalidExprException() {
        super();
    }
}



public class Calculator {

    public int evaluatePostFix(String s) throws InvalidPostfixException {
        MyStack privateStack = new MyStack();
        System.out.println("something");
        int lastIndex = 0;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '*' || s.charAt(i) == '/' || s.charAt(i) == '-' || s.charAt(i) == '+' ) {
                try {
                    lastIndex++;
                    int secondInt= (int)privateStack.pop();
                    int firstInt = (int)privateStack.pop();
                    switch(s.charAt(i)) {
                        case '*' : {
                            privateStack.push(firstInt*secondInt);
                            break;
                        }
                        case '+' : {
                            privateStack.push(firstInt+secondInt);
                            break;
                        }
                        case '-' : {
                            privateStack.push(firstInt-secondInt);
                            break;
                        }
                        case '/' : {
                            privateStack.push(firstInt/secondInt);
                            break;
                        }
                    }
                    if (i<s.length()-1 && s.charAt(i+1) == ' ') {
                        i++;
                        lastIndex++;
                    }
                            
                } catch (EmptyStackException e) {
                    throw new InvalidPostfixException();
                }  
            } else {
                if (i<s.length()-1) {
                    if (s.charAt(i+1) == '*' || s.charAt(i+1) == '/' ||
                     s.charAt(i+1) == '-' || s.charAt(i+1) == '+' || s.charAt(i+1) == ' ') {
                        privateStack.push(Integer.parseInt(s.substring(lastIndex, i+1)));
                        lastIndex = i+1;
                        if (s.charAt(i+1) == ' ') {
                            i++;
                            lastIndex++;
                        }
                            
                    }
                } else {
                    privateStack.push(Integer.parseInt(s.substring(lastIndex, i+1)));
                }
                
            }  
        }
        try {
            return (int)privateStack.top();
        } catch (EmptyStackException e) {
            throw new InvalidPostfixException();
        }
        
    }

    int Prec(char ch)
    {
        switch (ch)
        {
            case '+': {
                return 1;
            }
            case '-': {
                return 1;
            }
            case '*': {
                return 2;
            }
            case '/': {
                return 2;
            }
            default : {
                return -1;
            }
        }
    }

    public String convertExpression (String s) throws InvalidExprException {
        String result = "";
        s = s.replaceAll(" ", "");
        MyStack eqnStack = new MyStack();
        int lastIndex = 0;
        int numDif = 0;
         
        for (int i = 0; i<s.length(); ++i)
        {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                if (i<s.length()-1) {
                    if (!Character.isLetterOrDigit(s.charAt(i+1))) {
                        result = result + s.substring(lastIndex, i+1) + " ";
                        lastIndex = i+1;
                    }
                } else {
                    result = result + s.substring(lastIndex, i+1) + " ";
                    lastIndex = i+1;
                }
                numDif++;
            }  
            else if (c == '(') {
                eqnStack.push(c);
                lastIndex++;
            }
            
            else if (c == ')')
            {
                try {
                    while (!eqnStack.isEmpty() &&
                            (Character)eqnStack.top() != '(')
                        result = result + eqnStack.pop() + " ";
                } catch (EmptyStackException e) {
                    throw new InvalidExprException();
                }
                 
                    try {
                        eqnStack.pop();
                    } catch (EmptyStackException e) {
                        throw new InvalidExprException();
                    }
                lastIndex++;
            }
            else
            {
                try {
                    while (!eqnStack.isEmpty() && Prec(c)
                             <= Prec((Character)eqnStack.top())){
                       
                    result = result + eqnStack.pop() + " ";
                    }
                } catch (EmptyStackException e) {
                    throw new InvalidExprException();
                }
                eqnStack.push(c);
                lastIndex++;
                numDif--;
            }
      
        }
      
        while (!eqnStack.isEmpty()){

            try {
                if((Character)eqnStack.top() == '(')
                    throw new InvalidExprException();
                else
                    result = result + eqnStack.pop() + " ";
            } catch (EmptyStackException e) {
                throw new InvalidExprException();
            }
         }
         if (numDif<1)
            throw new InvalidExprException();
        return result.substring(0, result.length()-1);
    }
}
