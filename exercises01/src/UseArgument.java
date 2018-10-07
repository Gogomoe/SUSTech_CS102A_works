public class UseArgument {
   public static void main (String[] args) {
      System.out.print( "Hi, " + args[0] + ". How are you?" );
   }
}

/*

...>javac UseArgument.java

...>java UseArgument Alice
Hi, Alice, How are you?

...>java UseArgument Bob
Hi, Bob, How are you?

*/