//package PL109_10727220;
// alt+shift+b re compile
// java string 比較一定要用equal
// scanner 讀char是讀整行
// 路徑格式 C:\\Users\\user\\Desktop\\java\\test.txt
import java.lang.Exception;
import java.util.Scanner;

import jdk.internal.agent.resources.agent;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
class Read {
  static File f ;
  static Scanner sin ; // file input
  static int scount;
  static String sbuffer;
  static boolean seof;

  Read() { // use to read user
    scount = 0;
    sbuffer = "";
    seof = false;
    sin = new Scanner( System.in ); // normal input
    sin.nextLine(); // read the test num
    
  } // Read()

  Read( int i ) { // use to read file
    scount = 0;
    sbuffer = "";
    f = new File("C:\\Users\\user\\Desktop\\java\\test.txt"); // open file
    try {
      sin = new Scanner( f ); // file input
  
    } catch ( FileNotFoundException e ) { // read file must use try catch block
      e.printStackTrace();
    }
  
  } // Read()

  static char NextChar() throws No_more_input {
    if ( sin.hasNext() == true && scount == sbuffer.length() ) {
      scount = 0;
      sbuffer = sin.nextLine();
      sbuffer = sbuffer + "\n";
    } // if()
   
    scount ++;
    if ( sin.hasNext() == true || scount <= sbuffer.length() ) {
      return sbuffer.charAt( scount-1 );
    } // if()
    
    seof = true;
    throw new No_more_input( "EOF" );

  } // NextChar()

  static void SkipLine() {

    if ( scount == sbuffer.length() ) { // skip successful
      ;
    } // if()
    else {
      scount = sbuffer.length()-1;
    } // else
    

  } // SkipLine()

  boolean HasNext() {
    if ( sin.hasNext() == true ) {
      return true;
    } // if()
    else if ( scount < sbuffer.length() ) {
      return true;
    } // else if()
    else {
      return false;
    } // else

  } // HasNext()

} // class Read

class S_exp_tree {
  public Vector<String> mtoken;
  public Vector<String> mtype; 
  /*
  LEFT-PAREN       // '('
  RIGHT-PAREN      // ')'
  INT              // e.g., '123', '+123', '-123'
  STRING           // "string's (example)." (strings do not extend across lines)
  DOT              // '.'
  FLOAT            // '123.567', '123.', '.567', '+123.4', '-.123'
  NIL              // 'nil' or '#f', but not 'NIL' nor 'nIL'
  T                // 't' or '#t', but not 'T' nor '#T'
  QUOTE            // '
  SYMBOL
  */
  public boolean mis_null;
  public S_exp_tree mleft;
  public S_exp_tree mright;
  public S_exp_tree mback;

  S_exp_tree() {
    mis_null = true; // is false must bulid left and right
    mtoken = new Vector<String>();
    mtype = new Vector<String>();
  } // S_exp_tree()

} // class S_exp_tree

class Definition_table {
  public Vector<String> msymbol;
  public Vector<S_exp_tree> maddress; 

  Definition_table() {
    msymbol = new Vector<String>();
    maddress = new Vector<S_exp_tree>();
  } // Definition_table()

} // class Definition_table

class Unexpected_token extends Exception {
  private String mtoken;

  public Unexpected_token( String token ) {
    this.mtoken = token;
  } // Unexpected_token()

  String Get_error_token() {
    return mtoken;
  } // Get_error_token()

} // class Unexpected_token

class Unexpected_R_parenthesis extends Exception {
  private String mtoken;

  public Unexpected_R_parenthesis( String token ) {
    this.mtoken = token;
  } // Unexpected_R_parenthesis()

  String Get_error_token() {
    return mtoken;
  } // Get_error_token()

} // class Unexpected_R_parenthesis

class No_closing_quote extends Exception {
  private String mtoken;

  public No_closing_quote( String token ) {
    this.mtoken = token;
  } // No_closing_quote()

  String Get_error_token() {
    return mtoken;
  } // Get_error_token()

} // class No_closing_quote

class No_more_input extends Exception {
  private String mtoken;

  public No_more_input( String token ) {
    this.mtoken = token;
  } // No_more_input()

  String Get_error_token() {
    return mtoken;
  } // Get_error_token()

} // class No_more_input

class Non_list extends Exception {
  private String mtoken;

  public Non_list( String token ) {
    this.mtoken = token;
  } // Non_list()

  String Get_error_token() {
    return mtoken;
  } // Get_error_token()

} // class Non_list

class Incorrect_number_of_arguments extends Exception {
  private String mtoken;

  public Incorrect_number_of_arguments( String token ) {
    this.mtoken = token;
  } // Incorrect_number_of_arguments()

  String Get_error_token() {
    return mtoken;
  } // Get_error_token()

} // class Incorrect_number_of_arguments

class With_incorrect_argument_type extends Exception {
  private String mtoken;
  private S_exp_tree mpart;

  public With_incorrect_argument_type( S_exp_tree part ) {
    this.mpart = part;
  } // With_incorrect_argument_type()

  S_exp_tree Get_error_part() {
    return mpart;
  } // Get_error_part()

} // class With_incorrect_argument_type

class Attempt_to_apply_non_function extends Exception {
  private String mtoken;

  public Attempt_to_apply_non_function( String token ) {
    this.mtoken = token;
  } // Attempt_to_apply_non_function()

  String Get_error_token() {
    return mtoken;
  } // Get_error_token()

} // class Attempt_to_apply_non_function

class No_return_value extends Exception {
  private String mtoken;

  public No_return_value( String token ) {
    this.mtoken = token;
  } // No_return_value()

  String Get_error_token() {
    return mtoken;
  } // Get_error_token()

} // class No_return_value

class Unbound_symbol extends Exception {
  private String mtoken;

  public Unbound_symbol( String token ) {
    this.mtoken = token;
  } // Unbound_symbol()

  String Get_error_token() {
    return mtoken;
  } // Get_error_token()

} // class Unbound_symbol

class Division_by_zero extends Exception {
  private String mtoken;

  public Division_by_zero( String token ) {
    this.mtoken = token;
  } // Division_by_zero()

  String Get_error_token() {
    return mtoken;
  } // Get_error_token()

} // class Division_by_zero

class DEFINE_format extends Exception {
  private String mtoken;

  public DEFINE_format( String token ) {
    this.mtoken = token;
  } // DEFINE_format()

  String Get_error_token() {
    return mtoken;
  } // Get_error_token()

} // class DEFINE_format

class COND_format extends Exception {
  private String mtoken;

  public COND_format( String token ) {
    this.mtoken = token;
  } // COND_format()

  String Get_error_token() {
    return mtoken;
  } // Get_error_token()

} // class COND_format

class Level_of_clean_enviroment extends Exception {
  private String mtoken;

  public Level_of_clean_enviroment( String token ) {
    this.mtoken = token;
  } // Level_of_clean_enviroment()

  String Get_error_token() {
    return mtoken;
  } // Get_error_token()

} // class Level_of_clean_enviroment

class Level_of_define extends Exception {
  private String mtoken;

  public Level_of_define( String token ) {
    this.mtoken = token;
  } // Level_of_define()

  String Get_error_token() {
    return mtoken;
  } // Get_error_token()

} // class Level_of_define

class Lexial_analysis {
  private String mtoken;
  private char mnext_token;
  private boolean mnext_string;
  private boolean mnot_find;
  private int merror_column;
  private int mline;
  private int mcolumn;


  Lexial_analysis() {
    mtoken = "";
    mnext_token = '\0';
    mnext_string = false;
    mnot_find = true;
    mline = 1;
    mcolumn = 1;

  } // Lexial_analysis()

  boolean Not_a_token() {
    if ( mtoken.equals( "(" ) ) {
      return false;
    } // if()
    else if ( mtoken.equals( ")" ) ) {
      return false;      
    } // else if()
    else if ( mtoken.equals( "\'" ) ) {
      return false;      
    } // else if()
    else if ( mtoken.equals( "\"" )  ) {
      return false;      
    } // else if()
    else if ( mtoken.equals( "\n" ) ) {
      return false;      
    } // else if()
    else if ( mtoken.equals( ";" ) ) {
      return false;      
    } // else if()
    else {
      return true;
    } // else

  } // Not_a_token()

  boolean Is_white_space( char word ) {
    if ( word == ' ' ) {
      return true;
    } // if()
    else if (  word == '\t' ) {
      return true;
    } // else if()
    else if (  word == '\n' ) {
      return true;
    } // else if()
    else {
      return false;
    } // else
     
  } // Is_white_space()

  boolean Is_string_end( char temp ) { // watch the escape of \"
  
    if ( mtoken.length() == 0 ) {
      if ( temp == '\"' ) {
        mtoken = mtoken+temp;
        return false;
      } // if()
      else
        return true;

    } // if()
    else if ( mtoken.substring( mtoken.length()-1 ).equals( "\\" ) ) {
      if ( temp == '\"' ) {
        mtoken = mtoken.substring( 0, mtoken.length()-1 );
        mtoken = mtoken + "\"" ;
        return true;
      } // if() 
      else {
        return true;
      } // else

    } // else if()
    else if ( temp == '\"' ) {
      return false;
    } // else if()
    else {
      return true;
    } // else

  } // Is_string_end()

  void Find_next_token() throws No_more_input {
    boolean no_first = true;
    char temp = '\0';
    if ( mnext_token != '\0' ) { // have next token
      mtoken = Character.toString( mnext_token );
      no_first = false;
      merror_column = mcolumn; 
      if ( mnext_token == ';' ) {
        while ( temp != '\n' ) { // read the command
          temp = Read.NextChar();
          mcolumn++;
        } // while()

        mtoken = "\n";
        mnext_token = '\0';

      } // if()
      else { // clear
        mcolumn++;
        mnext_token = '\0';
      } // else

    } // if()

    while ( !mtoken.equals( "\n" ) && no_first ) { // have not next token
      temp = Read.NextChar();
      if ( Is_white_space( temp ) ) { // skip
        if ( temp == '\n' ) { // first char must catch
          mtoken = mtoken+temp;
          no_first = false;
          merror_column = mcolumn; 
        } // if()

      } // if()
      else if ( temp == ';' ) {
        merror_column = mcolumn;
        while ( temp != '\n' ) { // read the command
          temp = Read.NextChar();
        } // while()

        mtoken = Character.toString( temp );

      } // else if()
      else {
        mtoken = mtoken+temp;
        no_first = false;
        merror_column = mcolumn;
      } // else

      mcolumn++;

    } // while()

    if ( mtoken.equals( "\"" ) && mnext_string == false ) {
      mnext_string = true;
    } // if()

  } // Find_next_token()

  void Cut_token( char temp ) {

    if ( mnext_string == true && temp != '\n' && temp != '\"' ) { // string mode
      mtoken = mtoken+temp;
      mcolumn++;
    } // if()
    else if ( Is_white_space( temp ) ) {
      mnot_find = false; 
      if ( temp == '\n' ) { // \n is seperator of token
        mnext_token = temp;
      } // if()
      else {
        mcolumn++;
      } // else

    } // if()
    else if ( temp == '(' ) {
      mnext_token = temp;
      mnot_find = false; 
    } // else if()
    else if ( temp == ')' ) {
      mnext_token = temp;
      mnot_find = false;
    } // else if()
    else if ( temp == '\'' ) {
      mnext_token = temp;
      mnot_find = false;
    } // else if()
    else if ( temp == '\"' ) {
      mnext_token = temp;
      mnot_find = false;
    } // else if()
    else if ( temp == ';' ) { // comment
      mnext_token = temp;
      mnot_find = false;
    } // else if()
    else { // symbol or number
      mtoken = mtoken+temp;
      mcolumn++;
    } // else


  } // Cut_token()

  String Get_next_token( boolean string_mode ) throws No_more_input {
    mtoken = "";
    mnot_find = true;
    char temp = '\0';

    if ( mnext_string == false ) { // normal mode
      Find_next_token();
      while ( !mtoken.equals( "\n" ) && mnot_find && Not_a_token() ) { // read a token
        temp = Read.NextChar();
        Cut_token( temp );

      } // while()
      
    } // if()
    else { // string mode
      merror_column = mcolumn;
      if ( mnext_token == '\"' || mnext_token == '\n' ) { // second double quote
        Find_next_token();
        mnext_string = false;
      } // if()
      else { // read string
        while ( Is_string_end( temp ) && temp != '\n' ) {
          temp = Read.NextChar();
          Cut_token( temp );
          if ( temp == '\"' ) {
            mcolumn++;
          } // if()

        } // while()                
        
        if ( mtoken.equals( "\"" ) == true ) { // next " has been use
          mnext_token = '\0'; 
        } // if()
        else {
          mnext_token = temp; 
        } // else          

        if ( temp == '\"' ) {
          mcolumn--;
        } // if()

      } // else 
      
    } // else()

    /*
    System.out.print("token is:");
    System.out.print(mtoken);
    System.out.print("line is:");
    System.out.print(mline);
    System.out.print("error is:");
    System.out.print(merror_column);
    System.out.print("column is:");
    System.out.println(mcolumn);
    System.out.print("next token is:");
    System.out.println(mnext_token);
    */

    if ( string_mode ) { // return content or \n
      if ( mtoken.equals( "\n" ) ) { // error is happen and next line start is \n
        mnext_token = '\n';
      } // if()

      return mtoken;

    } // if()
    else if ( mtoken.equals( "\n" ) ) { // skip \n and find next line
      mline++;
      mcolumn = 1;
      return Get_next_token( false );
    } // else if()
    else {
      return mtoken;
    } // else

  } // Get_next_token()

  char Peek_next_token( boolean skip_line ) throws No_more_input {
    if ( mnext_token != '\0' ) { // have next token
      ;
    } // if()
    else if ( skip_line ) { // find next token skip '\n'
      while ( mnext_token == '\0' || mnext_token == ' ' || mnext_token == '\t'
              || mnext_token == '\n' ) {
        mnext_token = Read.NextChar();
        mcolumn++;
        if ( mnext_token == '\n' ) {
          mline++;
          mcolumn = 1;
        } // if()

      } // while()

      mcolumn--; // next token 仍然是他

    } // else if()
    else { // find next token in the same line 
      while ( mnext_token == '\0' || mnext_token == ' ' || mnext_token == '\t' ) {
        mnext_token = Read.NextChar();
        mcolumn++;

      } // while()
 
      mcolumn--; // next token 仍然是他

    } // else
    
    return mnext_token;

  } // Peek_next_token()

  int Get_error_line() {
    return mline;
  } // Get_error_line()

  int Get_error_column() {
    return merror_column;
  } // Get_error_column()

  void Reset() throws No_more_input { // new line
    if ( Peek_next_token( false ) == '\n' ) { // next line is line 1
      mline = 0;
    } // if()
    else if ( Peek_next_token( false ) == ';' ) { // next line is line 1
      mline = 0;
    } // else if()
    else {
      mline = 1;
    } // else
    
    mcolumn = mcolumn-merror_column-mtoken.length()+1; 

  } // Reset()

  void Clean_mnext_token() { // when error happen must abandon next_token
    mnext_token = '\0';
  } // Clean_mnext_token()

} // class Lexial_analysis

class Scheme {
  private boolean mhas_error;
  private Lexial_analysis mscanner;
  private S_exp_tree msexp ;
  private S_exp_tree mtemp_sexp ;
  private String mexit ;
  private int mspace_num;
  private boolean mfirst_atom;

  private Vector<Integer> mpara_num; 
  private Definition_table mtabel;

  Scheme() {
    mhas_error = false;
    mscanner = new Lexial_analysis();
    mexit = "";
    mspace_num = 0;
    mfirst_atom = false;
    mpara_num = new Vector<Integer>();
    mtabel = new Definition_table();
  } // Scheme()
  
  void Back_tree() {

    if ( mtemp_sexp == msexp.mback ) {
      mtemp_sexp = mtemp_sexp.mleft;
      Back_tree();

    } // if ()
    else if ( mtemp_sexp.mleft.mis_null == false && mtemp_sexp.mright.mis_null == true ) {
      mtemp_sexp = mtemp_sexp.mright;
    } // if ()
    else if ( mtemp_sexp == msexp ) { // end
      ; 
    } // else if()
    else {
      mtemp_sexp = mtemp_sexp.mback;
      Back_tree();

    } // else

  } // Back_tree()

  void Bulid_tree( String token, String type ) {
    if ( msexp.mis_null == true ) { // inital temp_sexp
      mtemp_sexp = msexp;
      msexp.mback = new S_exp_tree();
      msexp.mback.mleft = msexp ;
      msexp.mback.mright = msexp ;
      mtemp_sexp.mis_null = false;
      mtemp_sexp.mleft = new S_exp_tree();
      mtemp_sexp.mright = new S_exp_tree();
      mtemp_sexp.mleft.mback = mtemp_sexp;
      mtemp_sexp.mright.mback = mtemp_sexp;
      // ( or ' or atom
      mtemp_sexp.mtoken.add( token );
      mtemp_sexp.mtype.add( type );
  
    } // if()
    else {
      if ( token.equals( "(" ) ) {        
        if ( mtemp_sexp.mis_null == true ) { // empity
          if ( mtemp_sexp.mtype.isEmpty() ) {
            // normal
            mtemp_sexp.mis_null = false;
            mtemp_sexp.mleft = new S_exp_tree();
            mtemp_sexp.mright = new S_exp_tree();
            mtemp_sexp.mleft.mback = mtemp_sexp;
            mtemp_sexp.mright.mback = mtemp_sexp;
            mtemp_sexp.mtoken.add( token );
            mtemp_sexp.mtype.add( type );
          } // if()
          else { // special when ( (1) (7) ) case and run Back_tree()
            mtemp_sexp.mtype.remove( 0 );
            mtemp_sexp = mtemp_sexp.mback;
            mtemp_sexp.mright.mis_null = false;
            mtemp_sexp.mright.mleft = new S_exp_tree();
            mtemp_sexp.mright.mright = new S_exp_tree();
            mtemp_sexp.mright.mleft.mback = mtemp_sexp.mright;
            mtemp_sexp.mright.mright.mback = mtemp_sexp.mright;
            mtemp_sexp = mtemp_sexp.mright.mleft;
            Bulid_tree( token, type );
          } // else
          
        } // if()
        else if ( mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "QUOTE" ) ) { // only quote no (
          mtemp_sexp.mtoken.add( token );
          mtemp_sexp.mtype.add( type );
        } // else if ()
        else if ( mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "LEFT-PAREN" ) ) { // has (
          mtemp_sexp = mtemp_sexp.mleft;
          Bulid_tree( token, type );
        } // else if ()
        else if ( mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "DOT" ) ) { // has .
          // mtemp_sexp.mtoken.remove( mtemp_sexp.mtype.size()-1 ); // remove .
          // mtemp_sexp.mtype.remove( mtemp_sexp.mtype.size()-1 );
          mtemp_sexp.mtoken.add( token );
          mtemp_sexp.mtype.add( type );
        } // else if ()
        else { // is atom
          mtemp_sexp = mtemp_sexp.mback;
          mtemp_sexp.mright.mis_null = false;
          mtemp_sexp.mright.mleft = new S_exp_tree();
          mtemp_sexp.mright.mright = new S_exp_tree();
          mtemp_sexp.mright.mleft.mback = mtemp_sexp.mright;
          mtemp_sexp.mright.mright.mback = mtemp_sexp.mright;
          mtemp_sexp = mtemp_sexp.mright.mleft;
          Bulid_tree( token, type );

        } // else

      } // if()
      else if ( token.equals( "\'" ) ) {

        if ( mtemp_sexp.mis_null == true ) {
         
          if ( mtemp_sexp.mtype.isEmpty() ) {
            mtemp_sexp.mis_null = false;
            mtemp_sexp.mleft = new S_exp_tree();
            mtemp_sexp.mright = new S_exp_tree();
            mtemp_sexp.mleft.mback = mtemp_sexp;
            mtemp_sexp.mright.mback = mtemp_sexp;
            mtemp_sexp.mtoken.add( token );
            mtemp_sexp.mtype.add( type );
          } // if ()
          else { // special when ( (1) '(7) ) case and run Back_tree()
            mtemp_sexp.mtype.remove( 0 );
            mtemp_sexp = mtemp_sexp.mback;
            mtemp_sexp.mright.mis_null = false;
            mtemp_sexp.mright.mleft = new S_exp_tree();
            mtemp_sexp.mright.mright = new S_exp_tree();
            mtemp_sexp.mright.mleft.mback = mtemp_sexp.mright;
            mtemp_sexp.mright.mright.mback = mtemp_sexp.mright;
            mtemp_sexp = mtemp_sexp.mright.mleft;
            Bulid_tree( token, type );
          } // else

        } // if()
        else if ( mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "LEFT-PAREN" ) ) { // is (
          mtemp_sexp = mtemp_sexp.mleft;
          Bulid_tree( token, type );
        } // else if()
        else if ( mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "INT" ) ||
                  mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "STRING" ) ||
                  mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "FLOAT" ) ||
                  mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "NIL" ) ||
                  mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "T" ) ||
                  mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "SYMBOL" ) ) {  // is atom
          mtemp_sexp = mtemp_sexp.mback;
          mtemp_sexp.mright.mis_null = false;
          mtemp_sexp.mright.mleft = new S_exp_tree();
          mtemp_sexp.mright.mright = new S_exp_tree();
          mtemp_sexp.mright.mleft.mback = mtemp_sexp.mright;
          mtemp_sexp.mright.mright.mback = mtemp_sexp.mright;
          mtemp_sexp = mtemp_sexp.mright.mleft;
          Bulid_tree( token, type );
        } // else if()
        else { // dot or itself
          if ( mtemp_sexp.mtype.lastElement().equals( "DOT" ) ) {
            // mtemp_sexp.mtoken.remove( mtemp_sexp.mtype.size()-1 ); // remove .
            // mtemp_sexp.mtype.remove( mtemp_sexp.mtype.size()-1 );
          } // if()

          mtemp_sexp.mtoken.add( token );
          mtemp_sexp.mtype.add( type );
        } // else
        
      } // else if()
      else if ( type.equals( "INT" ) || type.equals( "STRING" ) || type.equals( "FLOAT" ) || 
                type.equals( "NIL" ) || type.equals( "T" ) || type.equals( "SYMBOL" ) ) {

        if ( mtemp_sexp.mis_null == true ) {
          if (  mtemp_sexp.mtype.isEmpty() == false ) { // special case for dot quote ( 
            mtemp_sexp.mtype.remove( 0 );
          } // if()
          
          mtemp_sexp.mis_null = false;
          mtemp_sexp.mleft = new S_exp_tree();
          mtemp_sexp.mright = new S_exp_tree();
          mtemp_sexp.mleft.mback = mtemp_sexp;
          mtemp_sexp.mright.mback = mtemp_sexp;
          mtemp_sexp.mleft.mis_null = false;
          mtemp_sexp.mleft.mleft = new S_exp_tree();
          mtemp_sexp.mleft.mright = new S_exp_tree();
          mtemp_sexp.mleft.mleft.mback = mtemp_sexp.mleft;
          mtemp_sexp.mleft.mright.mback = mtemp_sexp.mleft;
          mtemp_sexp.mleft.mtoken.add( token );
          mtemp_sexp.mleft.mtype.add( type );
          mtemp_sexp.mright.mtype.add( "ATOM" ); // case about ( 7 ( 1 2 3) 9 )
          mtemp_sexp = mtemp_sexp.mright;

        } // if()
        else if ( mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "QUOTE" ) ) {
          mtemp_sexp.mtoken.add( token );
          mtemp_sexp.mtype.add( type );
        } // else if()
        else if ( mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "LEFT-PAREN" ) ) {           
          mtemp_sexp.mleft.mis_null = false;
          mtemp_sexp.mleft.mleft = new S_exp_tree();
          mtemp_sexp.mleft.mright = new S_exp_tree();
          mtemp_sexp.mleft.mleft.mback = mtemp_sexp.mleft;
          mtemp_sexp.mleft.mright.mback = mtemp_sexp.mleft;
          mtemp_sexp.mleft.mtoken.add( token );
          mtemp_sexp.mleft.mtype.add( type ); 
          mtemp_sexp = mtemp_sexp.mleft;       

        } // else if ()
        else if ( mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "INT" ) ||
                  mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "STRING" ) ||
                  mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "FLOAT" ) ||
                  mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "NIL" ) ||
                  mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "T" ) ||
                  mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "SYMBOL" ) ) { 

          if ( mtemp_sexp.mback.mtoken.isEmpty() ) {
            mtemp_sexp = mtemp_sexp.mback.mright;
            Bulid_tree( token, type );
          } // if()
          else if ( mtemp_sexp.mback.mtype.get( mtemp_sexp.mback.mtype.size()-1 ).equals( "(" ) 
                    && mtemp_sexp.mback != msexp ) { // no the start ( and have two atom
           
            mtemp_sexp = mtemp_sexp.mback.mright;
            Bulid_tree( token, type );

          } // else if()
          else { 
            mtemp_sexp = mtemp_sexp.mback.mright;
            Bulid_tree( token, type );
          } // else
          

        } // else if()  
        else if ( mtemp_sexp.mtype.get( 0 ).equals( "DOT" ) ) {
          if ( type.equals( "NIL" ) ) {
            // mtemp_sexp.mtoken.remove( mtemp_sexp.mtype.size()-1 ); // remove .
            // mtemp_sexp.mtype.remove( mtemp_sexp.mtype.size()-1 ); 
          } // if()

          mtemp_sexp.mtoken.add( token );
          mtemp_sexp.mtype.add( type );
        } // else if()
        else {
          mtemp_sexp = mtemp_sexp.mback.mright;
          Bulid_tree( token, type );
        } // else

      } // else if()
      else if ( token.equals( ")" ) ) {
        if ( mtemp_sexp == msexp ) { // do nothing
          ;
        } // if()
        else if ( mtemp_sexp.mback.mleft.mleft.mis_null == true &&
                  mtemp_sexp.mback.mleft.mright.mis_null == true &&
                  mtemp_sexp.mis_null == true && mtemp_sexp.mback != msexp ) {

          mtemp_sexp = mtemp_sexp.mback;
          if ( mtemp_sexp == mtemp_sexp.mback.mleft ) { // case of ( (1) abc )
            mtemp_sexp = mtemp_sexp.mback;
            Back_tree(); // back to next empity right or head
            mtemp_sexp.mtype.add( "ATOM" ); // use to record special case
          } // if()
          else if ( mtemp_sexp.mtype.isEmpty() || 
                    mtemp_sexp.mtoken.get( 0 ).equals( "(" ) ) { 
                    // case of ( 1 abc ) or ( 1 ( abc ) )

            mtemp_sexp.mback.mright = mtemp_sexp.mleft;
            mtemp_sexp.mleft.mback = mtemp_sexp.mback;
            mtemp_sexp = mtemp_sexp.mback;            
            Back_tree(); // back to next empity right or head
            if ( mtemp_sexp != msexp && mtemp_sexp.mis_null == true ) {
              mtemp_sexp.mtype.add( "ATOM" ); // use to record special case
            } // if()
          } // else if()
          else { // case of ( 1 '( abc) )
            if ( mtemp_sexp.mtoken.get( mtemp_sexp.mtoken.size()-1 ).equals( "(" ) ) { 
              // need qouto but no need (
              mtemp_sexp.mtoken.remove( mtemp_sexp.mtoken.size()-1 );
            } // if()

            mtemp_sexp = mtemp_sexp.mback;
            Back_tree(); // back to next empity right or head
          } // else
          

        } // else if ()
        else if ( mtemp_sexp.mis_null == true ) { // ( ( 2 3 ) )
          mtemp_sexp = mtemp_sexp.mback;
          int i = 0;
          if ( mtemp_sexp.mleft.mtype.isEmpty() ) { // almost impossible
            mtemp_sexp.mright = mtemp_sexp.mleft.mright;
            mtemp_sexp.mleft = mtemp_sexp.mleft.mleft;
            mtemp_sexp.mright.mback = mtemp_sexp;
            mtemp_sexp.mleft.mback = mtemp_sexp;

          } // if()
          else if ( mtemp_sexp.mleft.mtoken.get( 0 ).equals( "." ) ) { 
                      // case of ( 1 . (2 . (3 . abc )

            if ( mtemp_sexp.mtype.isEmpty() ) {
              while ( i < mtemp_sexp.mleft.mtoken.size() ) {
                mtemp_sexp.mtoken.add( mtemp_sexp.mleft.mtoken.get( i ) );
                mtemp_sexp.mtype.add( mtemp_sexp.mleft.mtype.get( i ) );
                i++;
              } // while()
              
              mtemp_sexp.mright = mtemp_sexp.mleft.mright;
              mtemp_sexp.mleft = mtemp_sexp.mleft.mleft;
              mtemp_sexp.mleft.mback = mtemp_sexp;
              mtemp_sexp.mright.mback = mtemp_sexp;
              mtemp_sexp = mtemp_sexp.mback;
            } // if()
            else { // case of '( '( 7 5 ) ) )
              ;
            } // else

               
          } // else if()
          else if ( mtemp_sexp.mleft.mtoken.get( 0 ).equals( "\'" ) || 
                    mtemp_sexp.mleft.mtoken.get( 0 ).equals( "(" )  ) {   
                    
            mtemp_sexp = mtemp_sexp.mback;
            
          } // else if()
          else { // error

          } // else
          
          Back_tree(); // back to next empity right or head
          mtemp_sexp.mtype.add( "ATOM" );
        } // else if() 
        else if ( mtemp_sexp.mback.mleft == mtemp_sexp ) { // left atom or ( (1) 2)
          mtemp_sexp = mtemp_sexp.mback.mright;
          Bulid_tree( token, type );
        } // else if() 
        else {
          Back_tree();
        } // else
        
      } // else if()
      else if ( type.equals( "DOT" ) ) { // dot
        if ( mtemp_sexp.mis_null == true ) {
          if ( mtemp_sexp.mtype.isEmpty() ) {
            mtemp_sexp.mis_null = false;
            mtemp_sexp.mleft = new S_exp_tree();
            mtemp_sexp.mright = new S_exp_tree();
            mtemp_sexp.mleft.mback = mtemp_sexp;
            mtemp_sexp.mright.mback = mtemp_sexp;
            mtemp_sexp.mtoken.add( token );
            mtemp_sexp.mtype.add( type );
          } // if()
          else { // special case when case ( (1) . (3 7 5 ) ) and Back_tree() run
            mtemp_sexp.mtype.remove( 0 );
            mtemp_sexp = mtemp_sexp.mback;
            mtemp_sexp.mright.mis_null = false;
            mtemp_sexp.mright.mleft = new S_exp_tree();
            mtemp_sexp.mright.mright = new S_exp_tree();
            mtemp_sexp.mright.mleft.mback = mtemp_sexp.mright;
            mtemp_sexp.mright.mright.mback = mtemp_sexp.mright;
            mtemp_sexp = mtemp_sexp.mright.mleft;
            Bulid_tree( token, type );

          } // else

        } // if()
        else if ( mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "INT" ) ||
                  mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "STRING" ) ||
                  mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "FLOAT" ) ||
                  mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "NIL" ) ||
                  mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "T" ) ||
                  mtemp_sexp.mtype.get( mtemp_sexp.mtype.size()-1 ).equals( "SYMBOL" ) ) { // is atom

          S_exp_tree new_point = new S_exp_tree(); // maybe have two atom
          new_point.mis_null = false;
          new_point.mback = mtemp_sexp.mback;
          new_point.mleft = new S_exp_tree();
          new_point.mright = new S_exp_tree();
          new_point.mleft.mback = new_point;
          new_point.mright.mback = new_point;
          mtemp_sexp.mback.mright = new_point;
          mtemp_sexp = mtemp_sexp.mback.mright.mleft;
          Bulid_tree( token, type );

          new_point = new S_exp_tree(); // point to other

        } // else if()

      } // else if
      else { // other

      } // else

    } // else
    

  } // Bulid_tree()
  
  String Escape( String token ) {
    int index = 0;
    String slice = "";
    if ( token.indexOf( "\\\\" ) >= 0 ) {
      index = token.indexOf( "\\\\" );
      slice = token.substring( 0, index ) + "\\";
      return Escape( slice ) + Escape( token.substring( index+2, token.length() ) ); 
    } // if()
    else if ( token.indexOf( "\\n" ) >= 0 ) {
      index = token.indexOf( "\\n" );
      slice = token.substring( 0, index ) + "\n";
      return Escape( slice ) + Escape( token.substring( index+2, token.length() ) ); 
    } // else if()
    else if ( token.indexOf( "\\t" ) >= 0 ) {
      index = token.indexOf( "\\t" );
      slice = token.substring( 0, index ) + "\t";
      return Escape( slice ) + Escape( token.substring( index+2, token.length() ) ); 
    } // else if()
    else if ( token.indexOf( "\\\"" ) >= 0 ) {
      index = token.indexOf( "\\\"" );
      slice = token.substring( 0, index ) + "\"";
      return Escape( slice ) + Escape( token.substring( index+2, token.length() ) ); 
    } // else if()
    else {
      return token;
    } // else
    

  } // Escape()

  boolean Is_int( String token ) {
    int i = 0;                                       // first char is + or - or number
    boolean is_int = true;
    if ( token.charAt( i ) == '+' ||  token.charAt( i ) == '-' || 
         ( token.charAt( i )-'0' >= 0 &&  token.charAt( i )-'0' <= 9 ) ) { 
      i++;
      while ( i < token.length() ) {
        if ( token.charAt( i )-'0' < 0 ||  token.charAt( i )-'0' > 9 ) {
          is_int = false;
        } // if()

        i++;

      } // while()
      
    } // if()
    else {
      is_int = false;
    } // else

    if ( ( token.charAt( 0 ) == '+' ||  token.charAt( 0 ) == '-' ) &&  token.length() == 1 ) {
      is_int = false;
    } // if()

    return is_int;

  } // Is_int()

  boolean Is_float( String token ) {
    int i = 0;                                       
    boolean is_float = true;
    boolean have_dot = false;
    if ( token.charAt( i ) == '+' ||  token.charAt( i ) == '-' || // first char is + or - or . or number
         token.charAt( i ) == '.' || ( token.charAt( i )-'0' >= 0 &&  token.charAt( i )-'0' <= 9 ) ) { 
      if ( token.charAt( i ) == '.' ) {
        have_dot = true;
      } // if()

      i++;
      while ( i < token.length() ) {
        if ( ( token.charAt( i )-'0' < 0 || token.charAt( i )-'0' > 9 ) && have_dot == true ) {
          is_float = false;
        } // if()
        else if ( token.charAt( i ) == '.' && have_dot == false ) {
          have_dot = true; // first dot
        } // else if()
        else if ( token.charAt( i )-'0' < 0 ||  token.charAt( i )-'0' > 9 ) {
          is_float = false;
        } // else if()
        else {
          ;
        } // else

        i++;
        
      } // while()

      if ( token.equals( "+" ) ||  token.equals( "-" ) || token.equals( "." )
           || token.equals( "+." ) ||  token.equals( "-." ) ) {
        is_float = false;
      } // if()
      
    } // if()
    else {
      is_float = false;
    } // else

    return is_float;

  } // Is_float()

  int Token_type( String token ) {
    if ( Is_int( token ) ) {
      return 1;
    } // if()
    else if ( Is_float( token ) ) {
      return 2;
    } // else if()
    else if ( !token.equals( "." ) && !token.equals( ")" ) ) {
      return 3;
    } // else if()
    else {
      return 4;
    } // else

  } // Token_type()

  void ATOM( String token ) throws No_more_input, No_closing_quote, 
  Unexpected_R_parenthesis, Unexpected_token {
    if ( token.equals( "\"" ) ) { // string
      String temp ;
      temp = mscanner.Get_next_token( true );
      if ( temp.equals( "\"" ) ) { // second "
        temp = "\"" + temp ;
        Bulid_tree( Escape( temp ), "STRING" );
      } // if()
      else if ( temp.equals( "\n" ) ) { // only one "
        throw new No_closing_quote( temp );
      } // else if()
      else {
        temp = "\"" + temp ;    
        if ( mscanner.Get_next_token( true ).equals( "\"" ) ) { // second "
          temp = temp + "\"";
          Bulid_tree( Escape( temp ), "STRING" );
        } // if()
        else { // error
          throw new No_closing_quote( temp );
        } // else

      } // else 

    } // if()
    else if ( token.equals( "#f" ) || token.equals( "nil" ) ) { // nil
      Bulid_tree( "nil", "NIL" );
    } // else if()
    else if ( token.equals( "#t" ) || token.equals( "t" ) ) { // true
      Bulid_tree( "#t", "T" );
    } // else if()
    else if ( token.equals( "(" ) ) { // left
      if ( mscanner.Get_next_token( false ).equals( ")" ) ) {
        Bulid_tree( "nil", "NIL" );
      } // if()
      else { // error
        throw new Unexpected_R_parenthesis( token );
      } // else
    } // else if()
    else if ( Token_type( token ) == 3 ) { // symbol
      Bulid_tree( token, "SYMBOL" );
    } // if()
    else if ( Token_type( token ) == 1 ) { // int
      if ( token.charAt( 0 ) == '+' ) {
        Bulid_tree( token.substring( 1 ), "INT" );
      } // if()
      else {
        Bulid_tree( token, "INT" );
      } // else
      
    } // else if()
    else if ( Token_type( token ) == 2 ) { // float
      if ( token.charAt( 0 ) == '+' ) {
        Bulid_tree( String.format( "%.3f", Double.parseDouble( token ) ), "FLOAT" );
      } // if()
      else if ( token.charAt( 0 ) == '-' ) {
        Bulid_tree( String.format( "%.3f", Double.parseDouble( token ) ), "FLOAT" );
      } // else if() 
      else {
        Bulid_tree( String.format( "%.3f", Double.parseDouble( token ) ), "FLOAT" );
      } // else

    } // else if()
    else { // error 好像都算symbol
      throw new Unexpected_token( token );
    } // else

  } // ATOM()

  void S_exp( String token ) throws No_more_input, Unexpected_R_parenthesis,
  Unexpected_token, No_closing_quote {
    if ( token.equals( "(" ) ) { // leftd
      if ( mscanner.Peek_next_token( true ) == ')' ) {
        ATOM( token );
      } // if()
      else {
        Bulid_tree( token, "LEFT-PAREN" );
        S_exp( mscanner.Get_next_token( false ) );
        String temp ;
        temp = mscanner.Get_next_token( false );
        while ( !temp.equals( "." ) && !temp.equals( ")" ) ) { // token is s-exp
          S_exp( temp );
          temp = mscanner.Get_next_token( false );
        } // while()
        
        if ( temp.equals( "." ) ) {
          Bulid_tree( temp, "DOT" );
          S_exp( mscanner.Get_next_token( false ) );
          temp = mscanner.Get_next_token( false ); // read )
        } // if()
        
        if ( temp.equals( ")" ) ) {
          Bulid_tree( temp, "RIGHT-PAREN" );
        } // if()
        else { // error
          throw new Unexpected_R_parenthesis( temp );
        } // else

      } // else()

    } // if()
    else if ( token.equals( "\'" ) ) { // quote
      Bulid_tree( token, "QUOTE" );
      S_exp( mscanner.Get_next_token( false ) );
    } // else if()
    else { // atom
      ATOM( token );
    } // else

  } // S_exp()

  void ReadSExp() { // 判斷整句的EXP 
    mexit = "";
    msexp = new S_exp_tree(); // creat a new tree pointer
    try {
      S_exp( mscanner.Get_next_token( false ) ); // it will deal no token by itself

      mhas_error = false;

    } // try
    catch ( Unexpected_token ex ) { // atom or (
      mhas_error = true;
      msexp.mtoken.add( 0, "ERROR (unexpected token) : atom or '(' expected when token at Line "
                        + mscanner.Get_error_line() + " Column "+mscanner.Get_error_column() + 
                        " is >>"+ex.Get_error_token() + "<<" ) ;
      msexp.mtype.add( 0, "error" );
      Read.SkipLine();
      mscanner.Clean_mnext_token();
      
    } // catch 
    catch ( Unexpected_R_parenthesis ex ) { // )
      mhas_error = true;
      msexp.mtoken.add( 0, "ERROR (unexpected token) : ')' expected when token at Line " + 
                        mscanner.Get_error_line() +" Column "+mscanner.Get_error_column()+" is >>" + 
                        ex.Get_error_token() + "<<" );
      msexp.mtype.add( 0, "error" );
      Read.SkipLine();
      mscanner.Clean_mnext_token();
      
    } // catch 
    catch ( No_closing_quote ex ) {
      mhas_error = true;
      msexp.mtoken.add( 0, "ERROR (no closing quote) : END-OF-LINE encountered at Line "
                        + mscanner.Get_error_line() + " Column "+mscanner.Get_error_column() );
      msexp.mtype.add( 0, "error" );
      Read.SkipLine();
      mscanner.Clean_mnext_token();
      
    } // catch 
    catch ( No_more_input ex ) {
      mhas_error = true;
      msexp.mtoken.add( 0, "ERROR (no more input) : END-OF-FILE encountered" );
      msexp.mtype.add( 0, "error" );
      
    } // catch
    
    if ( No_end() ) {
      try {
        mscanner.Reset();
      } // try 
      catch ( No_more_input ex ) { // ignore no input
        Read.seof = false;
      } // catch   

    } // if()
    

  } // ReadSExp()

  boolean No_error() {
    return !mhas_error;
  } // No_error()

  boolean No_end() {
    if ( !mexit.equals( "(exit)" ) && !Read.seof ) {
      return true;
    } // if()
    else {
      return false;
    } // else

  } // No_end()

  boolean Is_primitive( String token ) {
    if ( token.equals( "cons" ) || token.equals( "list" ) || token.equals( "quote" ) ||
         token.equals( "define" ) || token.equals( "car" ) || token.equals( "cdr" ) ||
         token.equals( "atom?" ) || token.equals( "pair?" ) || token.equals( "list?" ) ||
         token.equals( "null?" ) || token.equals( "integer?" ) || token.equals( "real?" ) ||
         token.equals( "number?" ) || token.equals( "string?" ) || token.equals( "boolean?" ) ||
         token.equals( "symbol?" ) || token.equals( "+" ) || token.equals( "-" ) ||
         token.equals( "*" ) || token.equals( "/" ) || token.equals( "not" ) ||
         token.equals( "and" ) || token.equals( "or" ) || token.equals( ">" ) ||
         token.equals( ">=" ) || token.equals( "<" ) || token.equals( "<=" ) ||
         token.equals( "=" ) || token.equals( "string-append" ) || token.equals( "string>?" ) ||
         token.equals( "string<?" ) || token.equals( "string=?" ) || token.equals( "eqv?" ) ||
         token.equals( "equal?" ) || token.equals( "begin" ) || token.equals( "if" ) ||
         token.equals( "cond" ) || token.equals( "clean-environmentf" ) || token.equals( "exit" ) ) {

      return true;
    } // if()
    else {
      return false;
    } // else

  } // Is_primitive()

  S_exp_tree Primitive( String token ) {
    S_exp_tree temp = new S_exp_tree();
    temp.mis_null = false;
    temp.mtoken.add( "#<procedure "+ token +">" );
    temp.mtype.add( "STRING" );
    temp.mleft = new S_exp_tree();
    temp.mright = new S_exp_tree();
    return temp;
  } // Primitive()

  S_exp_tree Define( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree right;
    int loc;
    if ( start.mis_null == true ) {
      throw new DEFINE_format( "error" );
    } // if()

    if ( start.mleft.mis_null == false && start.mleft.mtoken.isEmpty() == false 
         && start.mleft.mtype.get( 0 ).equals( "SYMBOL" ) ) {
      loc = Find_table( start.mleft.mtoken.get( 0 ) );

    } // if()
    else {
      throw new DEFINE_format( "error" );
    } // else

    if ( start.mright.mis_null == false && start.mright.mtoken.isEmpty() == false ) {
      if ( start.mright.mleft.mis_null == false || start.mright.mright.mis_null == false ) {
        throw new DEFINE_format( "error" );
      } // if()

      right = EvalSExp( start.mright );   

    } // if()
    else if (  start.mright.mis_null == false && start.mright.mleft.mis_null == false
              && start.mright.mleft.mtoken.isEmpty() == false ) {

      if ( ! ( start.mright.mright.mis_null == false && start.mright.mright.mtype.size() == 2 &&
               start.mright.mright.mtype.get( 1 ).equals( "NIL" ) ) && 
           start.mright.mright.mis_null == false ) {
        throw new DEFINE_format( "error" );
      } // if()

      right = EvalSExp( start.mright.mleft );
      

    } // else if()
    else {
      throw new DEFINE_format( "error" );
    } // else
    
    if ( loc == -1 ) {
      if ( Is_primitive( start.mleft.mtoken.get( 0 ) ) ) {
        throw new DEFINE_format( "error" );
      } // if()

      mtabel.msymbol.add( start.mleft.mtoken.get( 0 ) );
      mtabel.maddress.add( right );

    } // if()
    else {
      mtabel.maddress.setElementAt( right, loc );
    } // else

    System.out.print( start.mleft.mtoken.get( 0 ) );
    System.out.println( " defined" );
    return new S_exp_tree();

  } // Define()

  S_exp_tree Cons( S_exp_tree start, boolean is_head ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree left;
    S_exp_tree right;
    int loc;
    if ( start.mis_null == false && start.mtoken.isEmpty() == true &&
         start.mleft.mis_null == false && start.mleft.mtoken.isEmpty() == false ) {
      left = start.mleft;
    } // if()
    else {
      throw new Incorrect_number_of_arguments( "cons" );
    } // else

    if ( start.mright.mis_null == false && start.mright.mtoken.isEmpty() == false ) {
      right = start.mright;
      if ( start.mright.mright.mis_null == false ) {
        throw new Incorrect_number_of_arguments( "cons" );
      } // if()

    } // if()
    else if ( start.mright.mis_null == false && start.mright.mleft.mis_null == false
              && start.mright.mleft.mtoken.isEmpty() == false ) {
      right = start.mright.mleft;
      if ( start.mright.mright.mis_null == false ) {
        throw new Incorrect_number_of_arguments( "cons" );
      } // if()

    } // else if()
    else {
      throw new Incorrect_number_of_arguments( "cons" );
    } // else

    temp = new S_exp_tree();
    temp.mis_null = false;
    temp.mtoken.add( "(" );
    temp.mtype.add( "LEFT-PAREN" );

    temp.mleft = EvalSExp( left );
    temp.mright = EvalSExp( right );


    if ( temp.mright.mis_null == false && temp.mright.mtype.isEmpty() == false &&
         temp.mright.mtype.get( 0 ).equals( "LEFT-PAREN" ) ) { // ( 3 ( 4 5) )
      right = new S_exp_tree();
      right.mis_null = false;
      right.mleft = temp.mright.mleft;
      right.mright = temp.mright.mright;
      temp.mright = right;
    } // if()    

    /*
    if ( is_head == true ) {
      temp.mtoken.add( "cons" );
      temp.mtype.add( "CONS");
    } // if()
    */
    if ( temp.mright.mis_null == false && temp.mright.mtype.isEmpty() == false && 
         ( temp.mright.mtype.get( 0 ).equals( "INT" ) || temp.mright.mtype.get( 0 ).equals( "STRING" ) ||
           temp.mright.mtype.get( 0 ).equals( "FLOAT" ) || temp.mright.mtype.get( 0 ).equals( "NIL" ) ||
           temp.mright.mtype.get( 0 ).equals( "T" ) || temp.mright.mtype.get( 0 ).equals( "SYMBOL" ) ) ) {

      temp.mright.mtoken.insertElementAt( ".", 0 );
      temp.mright.mtype.insertElementAt( "DOT", 0 );
    } // if()

    return temp;

  } // Cons()

  S_exp_tree List( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree walk;
    S_exp_tree temp_walk;
    if ( start.mis_null == false ) {
      temp = new S_exp_tree();
      temp.mis_null = false;
      temp.mtoken.add( "(" ); 
      temp.mtype.add( "LEFT-PAREN" );
      if ( start.mtoken.isEmpty() == false ) {
        temp.mleft = EvalSExp( start );
      } // if()
      else {
        temp.mleft = EvalSExp( start.mleft );
      } // else
      
      temp.mright = new S_exp_tree();
      temp_walk = temp.mright;
      walk = start.mright;
      while ( walk.mis_null == false && walk.mtoken.isEmpty() == true ) {
        temp_walk.mis_null = false;
        temp_walk.mleft = EvalSExp( walk.mleft );
        temp_walk.mright = new S_exp_tree();
        temp_walk = temp_walk.mright;
        walk = walk.mright;
      } // while()
  
      if ( walk.mis_null == true ) {
        return temp;
      } // if()
      else if ( walk.mtype.get( 0 ).equals( "DOT" ) ) {
        throw new Non_list( "error" );        
      } // else if()
      else { 
        temp_walk.mis_null = false;
        temp_walk.mleft = EvalSExp( walk );
        temp_walk.mright = new S_exp_tree();
        return temp;
      } // else

    } // if()
    else {
      temp = new S_exp_tree();
      temp.mis_null = false;
      temp.mtoken.add( "nil" ); 
      temp.mtype.add( "NIL" );
      temp.mleft = new S_exp_tree();
      temp.mright = new S_exp_tree();
      return temp;
    } // else
    
    


  } // List()

  S_exp_tree Quote( S_exp_tree start ) throws Incorrect_number_of_arguments {
    S_exp_tree temp;
    int i = 0;
    if ( start.mis_null == false && start.mtoken.isEmpty() == false ) {
      if ( start.mtoken.get( 0 ).equals( "\'" ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        while ( i < start.mtoken.size() ) {
          temp.mtoken.add( start.mtoken.get( i ) );
          i++;

        } // while()

        i = 0;
        while ( i < start.mtype.size() ) {
          temp.mtype.add( start.mtype.get( i ) );
          i++;

        } // while()

        temp.mtoken.remove( 0 );
        temp.mtype.remove( 0 );
        temp.mleft = start.mleft;
        temp.mright = start.mright;
        return temp;
      } // if()
      else {
        return start;
      } // else

    } // if()
    else if ( start.mis_null == false && start.mtoken.isEmpty() == true 
              && start.mleft.mtoken.isEmpty() == false && start.mright.mis_null == true ) {
      if ( start.mleft.mtoken.get( 0 ).equals( "\'" ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        while ( i < start.mleft.mtoken.size() ) {
          temp.mtoken.add( start.mleft.mtoken.get( i ) );
          i++;
          
        } // while()
          
        i = 0;
        while ( i < start.mleft.mtype.size() ) {
          temp.mtype.add( start.mleft.mtype.get( i ) );
          i++;
          
        } // while()
          
        temp.mtoken.remove( 0 );
        temp.mtype.remove( 0 );
        temp.mleft = start.mleft.mleft;
        temp.mright = start.mleft.mright;
        return temp;

      } // if()
      else {
        return start.mleft;
      } // else

    } // else if()
    else {
      throw new Incorrect_number_of_arguments( "Quote" );
    } // else


  } // Quote()

  S_exp_tree Car( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    if ( start.mis_null == false && start.mtoken.isEmpty() == false ) {
      if ( start.mtoken.get( 0 ).equals( "\'" ) || start.mtoken.get( 0 ).equals( "(" ) 
           || start.mtype.get( 0 ).equals( "SYMBOL" ) ) {
        temp = EvalSExp( start );    

        if ( temp.mleft.mis_null == true ) {
          System.out.print( "ERROR (" );
          System.out.print( "car" );
          throw new With_incorrect_argument_type( temp );
        } // if()
        else {
          return temp.mleft;
        } // else
        
      } // if()
      else {
        System.out.print( "ERROR (" );
        System.out.print( "car" );
        throw new With_incorrect_argument_type( start );
      } // else

    } // if()
    else if ( start.mis_null == false && start.mtoken.isEmpty() == true &&
              start.mleft.mtoken.isEmpty() == false && start.mright.mis_null == true ) {
      if ( start.mleft.mtoken.get( 0 ).equals( "\'" ) || start.mleft.mtoken.get( 0 ).equals( "(" ) 
           || start.mleft.mtype.get( 0 ).equals( "SYMBOL" ) ) {
        temp = EvalSExp( start.mleft );
        if ( temp.mleft.mis_null == true ) {
          System.out.print( "ERROR (" );
          System.out.print( "car" );
          throw new With_incorrect_argument_type( temp );
        } // if()
        else {
          return temp.mleft;
        } // else

      } // if()
      else {
        System.out.print( "ERROR (" );
        System.out.print( "car" );
        throw new With_incorrect_argument_type( start );
      } // else

    } // else if()
    else {
      throw new Incorrect_number_of_arguments( "Car" );
    } // else

  } // Car()

  S_exp_tree Cdr( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree temp2;
    if ( start.mis_null == false && start.mtoken.isEmpty() == false ) {

      if ( start.mtoken.get( 0 ).equals( "\'" ) || start.mtoken.get( 0 ).equals( "(" ) 
           || start.mtype.get( 0 ).equals( "SYMBOL" ) ) {

        temp = EvalSExp( start );
        if ( temp.mright.mis_null == false && temp.mright.mtoken.isEmpty() == false ) {
          temp2 = new S_exp_tree();
          temp2.mis_null = false;
          temp2.mtoken.add( "(" );
          temp2.mtype.add( "LEFT-PAREN" );
          temp2.mleft = temp.mright;
          temp2.mright = new S_exp_tree();
          return temp2;
        } // if()
        else if ( temp.mright.mis_null == false &&
                  temp.mright.mleft.mis_null == false && 
                  temp.mright.mleft.mtoken.isEmpty() == false ) {
          temp2 = new S_exp_tree();
          temp2.mis_null = false;
          temp2.mtoken.add( "(" );
          temp2.mtype.add( "LEFT-PAREN" );
          temp2.mleft = temp.mright.mleft;
          temp2.mright = temp.mright.mright;
          if ( temp.mright.mleft.mtype.get( 0 ).equals( "DOT" ) ) {
            return temp.mright.mleft;
          } // if()
          else {
            return temp2;
                      
          } // else
          
        } // else if
        else if ( temp.mis_null == false && temp.mtype.isEmpty() == false && 
                  temp.mtype.get( 0 ).equals( "LEFT-PAREN" ) &&
                  temp.mleft.mis_null == false && temp.mright.mis_null == true ) { //有左沒右邊 
          temp2 = new S_exp_tree();
          temp2.mis_null = false;
          temp2.mtoken.add( "nil" );
          temp2.mtype.add( "NIL" );
          temp2.mleft = new S_exp_tree();
          temp2.mright = new S_exp_tree();
          return temp2;
        } // else if
        else {
          throw new Incorrect_number_of_arguments( "Cdr" );
        } // else

      } // if()
      else {
        System.out.print( "ERROR (" );
        System.out.print( "cdr" );
        throw new With_incorrect_argument_type( start );
      } // else

    } // if()
    else if ( start.mis_null == false && start.mtoken.isEmpty() == true &&
              start.mleft.mtoken.isEmpty() == false && start.mright.mis_null == true ) {

      if ( start.mleft.mtoken.get( 0 ).equals( "\'" ) || start.mleft.mtoken.get( 0 ).equals( "(" ) 
           || start.mleft.mtype.get( 0 ).equals( "SYMBOL" ) ) {
        temp = EvalSExp( start.mleft );
        if ( temp.mright.mis_null == false && temp.mright.mtoken.isEmpty() == false ) {
          temp2 = new S_exp_tree();
          temp2.mis_null = false;
          temp2.mtoken.add( "(" );
          temp2.mtype.add( "LEFT-PAREN" );
          temp2.mleft = temp.mright;
          temp2.mright = new S_exp_tree();
          if ( temp.mright.mtype.get( 0 ).equals( "DOT" ) ) {
            return temp.mright;
          } // if()
          else {
            return temp2;
            
          } // else
          

        } // if()
        else if ( temp.mright.mis_null == false &&
                  temp.mright.mleft.mis_null == false && 
                  temp.mright.mleft.mtoken.isEmpty() == false ) {
          temp2 = new S_exp_tree();
          temp2.mis_null = false;
          temp2.mtoken.add( "(" );
          temp2.mtype.add( "LEFT-PAREN" );
          temp2.mleft = temp.mright.mleft;
          temp2.mright = temp.mright.mright;
          return temp2;
        } // else if
        else {
          throw new Incorrect_number_of_arguments( "Cdr" );
        } // else
          
      } // if()
      else {
        System.out.print( "ERROR (" );
        System.out.print( "cdr" );
        throw new With_incorrect_argument_type( start );
      } // else

    } // else if()
    else {
      throw new Incorrect_number_of_arguments( "Cdr" );
    } // else

  } // Cdr()

  S_exp_tree Equal_atom( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    if ( start.mis_null == false && start.mtoken.isEmpty() == false ) {
      temp = EvalSExp( start );
      if ( temp.mtoken.isEmpty() == false && 
           ( temp.mtype.get( 0 ).equals( "INT" ) || temp.mtype.get( 0 ).equals( "String" ) || 
             temp.mtype.get( 0 ).equals( "NIL" ) || temp.mtype.get( 0 ).equals( "T" ) || 
             temp.mtype.get( 0 ).equals( "FLOAT" ) || temp.mtype.get( 0 ).equals( "SYMBOL" ) ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else if ( temp.mtoken.isEmpty() == false ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else if()
      else { // error
        throw new Incorrect_number_of_arguments( "atom?" );
      } // else

    } // if()
    else if ( start.mis_null == false && start.mtoken.isEmpty() == true
              && start.mleft.mtoken.isEmpty() == false
              && start.mright.mtoken.isEmpty() == true ) {
      temp = EvalSExp( start.mleft );
      if ( temp.mtoken.isEmpty() == false && 
           ( temp.mtype.get( 0 ).equals( "INT" ) || temp.mtype.get( 0 ).equals( "String" ) || 
             temp.mtype.get( 0 ).equals( "NIL" ) || temp.mtype.get( 0 ).equals( "T" ) || 
             temp.mtype.get( 0 ).equals( "FLOAT" ) || temp.mleft.mtype.get( 0 ).equals( "SYMBOL" ) ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else if ( temp.mtoken.isEmpty() == false ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else if()
      else { // error
        throw new Incorrect_number_of_arguments( "atom?" );
      } // else

    } // else if()
    else {
      throw new Incorrect_number_of_arguments( "atom?" );
    } // else


  } // Equal_atom()

  S_exp_tree Equal_pair( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp; 
    if ( start.mis_null == false && start.mtoken.isEmpty() == false ) {
      temp = EvalSExp( start );
      if ( temp.mleft.mtoken.isEmpty() == false && 
           ( temp.mright.mtoken.isEmpty() == false || 
             temp.mright.mleft.mtoken.isEmpty() == false ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else { // error
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // if()
    else if ( start.mis_null == false && start.mtoken.isEmpty() == true
              && start.mleft.mtoken.isEmpty() == false
              && start.mright.mtoken.isEmpty() == true ) {
      temp = EvalSExp( start.mleft );
      if ( temp.mleft.mtoken.isEmpty() == false && 
           ( temp.mright.mtoken.isEmpty() == false || 
             temp.mright.mleft.mtoken.isEmpty() == false ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else { // error
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // else if()
    else {
      throw new Incorrect_number_of_arguments( "pair?" );
    } // else

  } // Equal_pair()

  S_exp_tree Equal_list( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree walk;
    if ( start.mis_null == false && start.mtoken.isEmpty() == false ) {
      start = EvalSExp( start );
      if ( start.mis_null == false && start.mtype.get( 0 ).equals( "LEFT-PAREN" ) ) {
        ;
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

      walk = start.mright;
      while ( walk.mis_null == false && walk.mtoken.isEmpty() == true ) {
        walk = walk.mright;
      } // while()
  
      if ( walk.mis_null == true ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else { 
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // if()
    else if ( start.mis_null == false && start.mtoken.isEmpty() == true
              && start.mleft.mtoken.isEmpty() == false
              && start.mright.mtoken.isEmpty() == true ) {
      start = EvalSExp( start.mleft );
      if ( start.mis_null == false && start.mtype.get( 0 ).equals( "LEFT-PAREN" ) ) {
        ;
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

      walk = start.mright;
      while ( walk.mis_null == false && walk.mtoken.isEmpty() == true ) {
        walk = walk.mright;
      } // while()
  
      if ( walk.mis_null == true ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else { 
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // else if()
    else {
      throw new Incorrect_number_of_arguments( "list?" );
    } // else


  } // Equal_list()

  S_exp_tree Equal_null( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    if ( start.mis_null == false && start.mtoken.isEmpty() == false ) {
      start = EvalSExp( start );
      if ( start.mis_null == false && start.mtype.get( 0 ).equals( "NIL" ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // if()
    else if ( start.mis_null == false && start.mtoken.isEmpty() == true
              && start.mleft.mtoken.isEmpty() == false
              && start.mright.mtoken.isEmpty() == true ) {
      start = EvalSExp( start.mleft );
      if ( start.mis_null == false && start.mtype.get( 0 ).equals( "NIL" ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // else if()
    else {
      throw new Incorrect_number_of_arguments( "null?" );
    } // else


  } // Equal_null()

  S_exp_tree Equal_int( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    if ( start.mis_null == false && start.mtoken.isEmpty() == false ) {
      start = EvalSExp( start );
      if ( start.mis_null == false && start.mtype.get( 0 ).equals( "INT" ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // if()
    else if ( start.mis_null == false && start.mtoken.isEmpty() == true
              && start.mleft.mtoken.isEmpty() == false
              && start.mright.mtoken.isEmpty() == true ) {
      start = EvalSExp( start.mleft );
      if ( start.mis_null == false && start.mtype.get( 0 ).equals( "INT" ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // else if()
    else {
      throw new Incorrect_number_of_arguments( "integer?" );
    } // else


  } // Equal_int()

  S_exp_tree Equal_real( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    if ( start.mis_null == false && start.mtoken.isEmpty() == false ) {
      start = EvalSExp( start );
      if ( start.mis_null == false && 
           ( start.mtype.get( 0 ).equals( "INT" ) || start.mtype.get( 0 ).equals( "FLOAT" ) ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // if()
    else if ( start.mis_null == false && start.mtoken.isEmpty() == true
              && start.mleft.mtoken.isEmpty() == false
              && start.mright.mtoken.isEmpty() == true ) {
      start = EvalSExp( start.mleft );
      if ( start.mis_null == false && 
           ( start.mtype.get( 0 ).equals( "INT" ) || start.mtype.get( 0 ).equals( "FLOAT" ) ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // else if()
    else {
      throw new Incorrect_number_of_arguments( "real?" );
    } // else


  } // Equal_real()

  S_exp_tree Equal_number( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {

    if ( start.mis_null == false && start.mtoken.isEmpty() == false ) {
      return Equal_real( start );

    } // if()
    else if ( start.mis_null == false && start.mtoken.isEmpty() == true
              && start.mleft.mtoken.isEmpty() == false
              && start.mright.mtoken.isEmpty() == true ) {
      return Equal_real( start.mleft );

    } // else if()
    else {
      throw new Incorrect_number_of_arguments( "number?" );
    } // else


  } // Equal_number()

  S_exp_tree Equal_string( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    if ( start.mis_null == false && start.mtoken.isEmpty() == false ) {
      start = EvalSExp( start );
      if ( start.mis_null == false && start.mtype.get( 0 ).equals( "STRING" ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // if()
    else if ( start.mis_null == false && start.mtoken.isEmpty() == true
              && start.mleft.mtoken.isEmpty() == false
              && start.mright.mtoken.isEmpty() == true ) {
      start = EvalSExp( start.mleft );
      if ( start.mis_null == false && start.mtype.get( 0 ).equals( "STRING" ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // else if()
    else {
      throw new Incorrect_number_of_arguments( "string?" );
    } // else


  } // Equal_string()

  S_exp_tree Equal_boolean( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    if ( start.mis_null == false && start.mtoken.isEmpty() == false ) {
      start = EvalSExp( start );
      if ( start.mis_null == false && 
           ( start.mtype.get( 0 ).equals( "T" ) || start.mtype.get( 0 ).equals( "NIL" ) ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // if()
    else if ( start.mis_null == false && start.mtoken.isEmpty() == true
              && start.mleft.mtoken.isEmpty() == false
              && start.mright.mtoken.isEmpty() == true ) {
      start = EvalSExp( start.mleft );
      if ( start.mis_null == false && 
           ( start.mtype.get( 0 ).equals( "T" ) || start.mtype.get( 0 ).equals( "NIL" ) ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // else if()
    else {
      throw new Incorrect_number_of_arguments( "boolean?" );
    } // else


  } // Equal_boolean()

  S_exp_tree Equal_symbol( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    if ( start.mis_null == false && start.mtoken.isEmpty() == false ) {
      temp = EvalSExp( start );
      if ( start.mis_null == false && temp.mtype.get( 0 ).equals( "SYMBOL" ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // if()
    else if ( start.mis_null == false && start.mtoken.isEmpty() == true
              && start.mleft.mtoken.isEmpty() == false
              && start.mright.mtoken.isEmpty() == true ) {
      temp = EvalSExp( start.mleft );
      if ( start.mis_null == false && temp.mtype.get( 0 ).equals( "SYMBOL" ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // else if()
    else {
      throw new Incorrect_number_of_arguments( "symbol?" );
    } // else


  } // Equal_symbol()

  S_exp_tree Find_string( S_exp_tree start, String function_name ) throws Non_list, 
  Incorrect_number_of_arguments, With_incorrect_argument_type, Attempt_to_apply_non_function, 
  No_return_value, Unbound_symbol, Division_by_zero, DEFINE_format, COND_format,
  Level_of_clean_enviroment, Level_of_define {
    int loc ;
    if ( start.mtype.isEmpty() == true ) {
      if ( start.mleft.mis_null == false && start.mleft.mtoken.isEmpty() == false && 
           start.mleft.mtype.get( 0 ).equals( "LEFT-PAREN" ) ) { 
        start.mleft = EvalSExp( start.mleft );
      } // if()
      else if ( start.mleft.mis_null == false && start.mleft.mtoken.isEmpty() == false && 
                start.mleft.mtype.get( 0 ).equals( "SYMBOL" ) ) {
        loc = Find_table( start.mleft.mtoken.get( 0 ) );
        if ( loc == -1 ) {
          throw new Unbound_symbol( start.mleft.mtoken.get( 0 ) );
        } // if()
        else {
          start.mleft = mtabel.maddress.get( loc );
        } // else

      } // else if()

      if ( start.mleft.mis_null == false && start.mleft.mtoken.isEmpty() == false && 
           start.mleft.mtype.get( 0 ).equals( "STRING" ) ) { 
        return start.mleft;
      } // if()
      else {
        System.out.print( "ERROR (" );
        System.out.print( function_name );
        throw new With_incorrect_argument_type( EvalSExp( start.mleft ) );
      } // else

    } // if()
    else {
      if ( start.mis_null == false && start.mtoken.isEmpty() == false && 
           start.mtype.get( 0 ).equals( "LEFT-PAREN" ) ) { 
        start = EvalSExp( start );
      } // if()
      else if ( start.mis_null == false && start.mtoken.isEmpty() == false && 
                start.mtype.get( 0 ).equals( "SYMBOL" ) ) { 
        loc = Find_table( start.mtoken.get( 0 ) );
        if ( loc == -1 ) {
          throw new Unbound_symbol( start.mtoken.get( 0 ) );
        } // if()
        else {
          start = mtabel.maddress.get( loc );
        } // else

      } // else if()

      if ( start.mis_null == false && start.mtoken.isEmpty() == false && 
           start.mtype.get( 0 ).equals( "STRING" ) ) { 
        return start;
      } // if()
      else {
        System.out.print( "ERROR (" );
        System.out.print( function_name );
        throw new With_incorrect_argument_type( EvalSExp( start ) );
      } // else

    } // else
    
  } // Find_string()

  void Check_string( S_exp_tree start, String function_name ) throws Non_list, 
  Incorrect_number_of_arguments, With_incorrect_argument_type, Attempt_to_apply_non_function, 
  No_return_value, Unbound_symbol, Division_by_zero, DEFINE_format, COND_format,
  Level_of_clean_enviroment, Level_of_define {
    S_exp_tree temp;
    temp = start;
    while( temp.mis_null == false ) {
      if ( temp.mleft.mis_null == false ) { // only one para
        Find_string( temp.mleft, function_name );
      } // if()

      if ( temp.mright.mis_null == false ) {
        Find_string( temp.mright, function_name );    
      } // if()

      temp = temp.mright;

    } // while()

  } // Check_string()

  S_exp_tree Find_num( S_exp_tree start, String function_name ) throws Non_list, 
  Incorrect_number_of_arguments, With_incorrect_argument_type, Attempt_to_apply_non_function, 
  No_return_value, Unbound_symbol, Division_by_zero, DEFINE_format, COND_format,
  Level_of_clean_enviroment, Level_of_define {
    int loc ;
    if ( start.mtype.isEmpty() == true ) {
      if ( start.mleft.mis_null == false && start.mleft.mtoken.isEmpty() == false && 
           start.mleft.mtype.get( 0 ).equals( "LEFT-PAREN" ) ) { 
        start.mleft = EvalSExp( start.mleft );
      } // if()
      else if ( start.mleft.mis_null == false && start.mleft.mtoken.isEmpty() == false && 
                start.mleft.mtype.get( 0 ).equals( "SYMBOL" ) ) {
        loc = Find_table( start.mleft.mtoken.get( 0 ) );
        if ( loc == -1 ) {
          throw new Unbound_symbol( start.mleft.mtoken.get( 0 ) );
        } // if()
        else {
          start.mleft = mtabel.maddress.get( loc );
        } // else

      } // else if()

      if ( start.mleft.mis_null == false && start.mleft.mtoken.isEmpty() == false && 
           ( start.mleft.mtype.get( 0 ).equals( "INT" ) || 
             start.mleft.mtype.get( 0 ).equals( "FLOAT" ) ) ) { 
        return start.mleft;
      } // if()
      else {
        System.out.print( "ERROR (" );
        System.out.print( function_name );
        throw new With_incorrect_argument_type( EvalSExp( start.mleft ) );
      } // else

    } // if()
    else {
      if ( start.mis_null == false && start.mtoken.isEmpty() == false && 
           start.mtype.get( 0 ).equals( "LEFT-PAREN" ) ) { 
        start = EvalSExp( start );
      } // if()
      else if ( start.mis_null == false && start.mtoken.isEmpty() == false && 
                start.mtype.get( 0 ).equals( "SYMBOL" ) ) { 
        loc = Find_table( start.mtoken.get( 0 ) );
        if ( loc == -1 ) {
          throw new Unbound_symbol( start.mtoken.get( 0 ) );
        } // if()
        else {
          start = mtabel.maddress.get( loc );
        } // else

      } // else if()

      if ( start.mis_null == false && start.mtoken.isEmpty() == false && 
           ( start.mtype.get( 0 ).equals( "INT" ) || start.mtype.get( 0 ).equals( "FLOAT" ) ) ) { 
        return start;
      } // if()
      else {
        System.out.print( "ERROR (" );
        System.out.print( function_name );
        throw new With_incorrect_argument_type( EvalSExp( start ) );
      } // else

    } // else
    
  } // Find_num()

  void Check_num( S_exp_tree start, String function_name ) throws Non_list, 
  Incorrect_number_of_arguments, With_incorrect_argument_type, Attempt_to_apply_non_function, 
  No_return_value, Unbound_symbol, Division_by_zero, DEFINE_format, COND_format,
  Level_of_clean_enviroment, Level_of_define {
    S_exp_tree temp;
    temp = start;
    while( temp.mis_null == false ) {
      if ( temp.mleft.mis_null == false ) { // only one para
        Find_num( temp.mleft, function_name );
      } // if()

      if ( temp.mright.mis_null == false ) {
        Find_num( temp.mright, function_name );    
      } // if()

      temp = temp.mright;

    } // while()

  } // Check_num()

  S_exp_tree Find_bool( S_exp_tree start, String function_name ) throws Non_list, 
  Incorrect_number_of_arguments, With_incorrect_argument_type, Attempt_to_apply_non_function, 
  No_return_value, Unbound_symbol, Division_by_zero, DEFINE_format, COND_format,
  Level_of_clean_enviroment, Level_of_define {
    int loc ;
    if ( start.mtype.isEmpty() == true ) {
      if ( start.mleft.mis_null == false && start.mleft.mtoken.isEmpty() == false && 
           start.mleft.mtype.get( 0 ).equals( "LEFT-PAREN" ) ) { 
        start.mleft = EvalSExp( start.mleft );
      } // if()
      else if ( start.mleft.mis_null == false && start.mleft.mtoken.isEmpty() == false && 
                start.mleft.mtype.get( 0 ).equals( "SYMBOL" ) ) {
        loc = Find_table( start.mleft.mtoken.get( 0 ) );
        if ( loc == -1 ) {
          throw new Unbound_symbol( start.mleft.mtoken.get( 0 ) );
        } // if()
        else {
          start.mleft = mtabel.maddress.get( loc );
        } // else

      } // else if()

      return start.mleft;

    } // if()
    else {
      if ( start.mis_null == false && start.mtoken.isEmpty() == false && 
           start.mtype.get( 0 ).equals( "LEFT-PAREN" ) ) { 
        start = EvalSExp( start );
      } // if()
      else if ( start.mis_null == false && start.mtoken.isEmpty() == false && 
                start.mtype.get( 0 ).equals( "SYMBOL" ) ) { 
        loc = Find_table( start.mtoken.get( 0 ) );
        if ( loc == -1 ) {
          throw new Unbound_symbol( start.mtoken.get( 0 ) );
        } // if()
        else {
          start = mtabel.maddress.get( loc );
        } // else

      } // else if()

      return start;

    } // else
    
  } // Find_bool()

  S_exp_tree Add( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree left_num;
    S_exp_tree right_num;
    int num1;
    double num1f;
    int num2;
    double num2f;

    if ( start.mis_null == false && start.mtoken.isEmpty() == true ) {
      left_num = Find_num( start.mleft, "+" );
      mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );
      if ( start.mright.mis_null == false ) {
        right_num = Find_num( start.mright, "+" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );    
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          return start.mleft;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( "+" );
        } // else

      } // else

      if ( left_num.mtype.get( 0 ).equals( "FLOAT" ) || right_num.mtype.get( 0 ).equals( "FLOAT" ) ) {
        num1f = Double.parseDouble( left_num.mtoken.get( 0 ) );
        num2f = Double.parseDouble( right_num.mtoken.get( 0 ) );
        num1f = num1f+num2f;
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mleft = new S_exp_tree();
        temp.mleft.mis_null = false;
        temp.mleft.mback = temp;
        temp.mleft.mtoken.add( String.format( "%.3f", num1f ) );
        temp.mleft.mtype.add( "FLOAT" );
        temp.mright = start.mright.mright;
        temp.mleft.mleft = new S_exp_tree();
        temp.mleft.mright = new S_exp_tree();
      } // if()
      else {
        num1 = Integer.parseInt( left_num.mtoken.get( 0 ) );  
        num2 = Integer.parseInt( right_num.mtoken.get( 0 ) );  
        num1 = num1+num2;
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mleft = new S_exp_tree();
        temp.mleft.mis_null = false;
        temp.mleft.mback = temp;
        temp.mleft.mtoken.add( Integer.toString( num1 ) );
        temp.mleft.mtype.add( "INT" );
        temp.mright = start.mright.mright;
        temp.mleft.mleft = new S_exp_tree();
        temp.mleft.mright = new S_exp_tree();
      } // else
      
      Check_num( start, "+" );
      return Add( temp );

    } // if()
    else {
      throw new Incorrect_number_of_arguments( "+" );
    } // else

  } // Add()

  S_exp_tree Subtract( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree left_num;
    S_exp_tree right_num;
    int num1;
    double num1f;
    int num2;
    double num2f;

    if ( start.mis_null == false && start.mtoken.isEmpty() == true ) {
      left_num = Find_num( start.mleft, "-" );
      mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );
      if ( start.mright.mis_null == false ) {
        right_num = Find_num( start.mright, "-" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );    
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          return start.mleft;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( "-" );
        } // else

      } // else

      if ( left_num.mtype.get( 0 ).equals( "FLOAT" ) || right_num.mtype.get( 0 ).equals( "FLOAT" ) ) {
        num1f = Double.parseDouble( left_num.mtoken.get( 0 ) );
        num2f = Double.parseDouble( right_num.mtoken.get( 0 ) );
        num1f = num1f-num2f;
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mleft = new S_exp_tree();
        temp.mleft.mis_null = false;
        temp.mleft.mback = temp;
        temp.mleft.mtoken.add( String.format( "%.3f", num1f ) );
        temp.mleft.mtype.add( "FLOAT" );
        temp.mright = start.mright.mright;
        temp.mleft.mleft = new S_exp_tree();
        temp.mleft.mright = new S_exp_tree();
      } // if()
      else {
        num1 = Integer.parseInt( left_num.mtoken.get( 0 ) );  
        num2 = Integer.parseInt( right_num.mtoken.get( 0 ) );  
        num1 = num1-num2;
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mleft = new S_exp_tree();
        temp.mleft.mis_null = false;
        temp.mleft.mback = temp;
        temp.mleft.mtoken.add( Integer.toString( num1 ) );
        temp.mleft.mtype.add( "INT" );
        temp.mright = start.mright.mright;
        temp.mleft.mleft = new S_exp_tree();
        temp.mleft.mright = new S_exp_tree();
      } // else
    
      Check_num( start, "-" );
      return Subtract( temp );
    } // if()
    else {
      throw new Incorrect_number_of_arguments( "-" );
    } // else
    

  } // Subtract()

  S_exp_tree Multiply( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree left_num;
    S_exp_tree right_num;
    int num1;
    double num1f;
    int num2;
    double num2f;

    if ( start.mis_null == false && start.mtoken.isEmpty() == true ) {
      left_num = Find_num( start.mleft, "*" );
      mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );
      if ( start.mright.mis_null == false ) {
        right_num = Find_num( start.mright, "*" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );    
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          return start.mleft;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( "*" );
        } // else

      } // else

      if ( left_num.mtype.get( 0 ).equals( "FLOAT" ) || right_num.mtype.get( 0 ).equals( "FLOAT" ) ) {
        num1f = Double.parseDouble( left_num.mtoken.get( 0 ) );
        num2f = Double.parseDouble( right_num.mtoken.get( 0 ) );
        num1f = num1f*num2f;
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mleft = new S_exp_tree();
        temp.mleft.mis_null = false;
        temp.mleft.mback = temp;
        temp.mleft.mtoken.add( String.format( "%.3f", num1f ) );
        temp.mleft.mtype.add( "FLOAT" );
        temp.mright = start.mright.mright;
        temp.mleft.mleft = new S_exp_tree();
        temp.mleft.mright = new S_exp_tree();
      } // if()
      else {
        num1 = Integer.parseInt( left_num.mtoken.get( 0 ) );  
        num2 = Integer.parseInt( right_num.mtoken.get( 0 ) );  
        num1 = num1*num2;
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mleft = new S_exp_tree();
        temp.mleft.mis_null = false;
        temp.mleft.mback = temp;
        temp.mleft.mtoken.add( Integer.toString( num1 ) );
        temp.mleft.mtype.add( "INT" );
        temp.mright = start.mright.mright;
        temp.mleft.mleft = new S_exp_tree();
        temp.mleft.mright = new S_exp_tree();
      } // else
    
      Check_num( start, "*" );
      return Multiply( temp );

    } // if()
    else {
      throw new Incorrect_number_of_arguments( "*" );
    } // else

  } // Multiply()

  S_exp_tree Divide( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree left_num;
    S_exp_tree right_num;
    int num1;
    double num1f;
    int num2;
    double num2f;

    if ( start.mis_null == false && start.mtoken.isEmpty() == true ) {
      left_num = Find_num( start.mleft, "/" );
      mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );
      if ( start.mright.mis_null == false ) {
        right_num = Find_num( start.mright, "/" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );    
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          return start.mleft;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( "/" );
        } // else

      } // else

      if ( left_num.mtype.get( 0 ).equals( "FLOAT" ) || right_num.mtype.get( 0 ).equals( "FLOAT" ) ) {
        num1f = Double.parseDouble( left_num.mtoken.get( 0 ) );
        num2f = Double.parseDouble( right_num.mtoken.get( 0 ) );
        if ( num2f == 0.0 ) {
          throw new Division_by_zero( "/" );
        } // if()

        num1f = num1f/num2f;
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mleft = new S_exp_tree();
        temp.mleft.mis_null = false;
        temp.mleft.mback = temp;
        temp.mleft.mtoken.add( String.format( "%.3f", num1f ) );
        temp.mleft.mtype.add( "FLOAT" );
        temp.mright = start.mright.mright;
        temp.mleft.mleft = new S_exp_tree();
        temp.mleft.mright = new S_exp_tree();
      } // if()
      else {
        num1 = Integer.parseInt( left_num.mtoken.get( 0 ) );  
        num2 = Integer.parseInt( right_num.mtoken.get( 0 ) );  
        if ( num2 == 0 ) {
          throw new Division_by_zero( "/" );
        } // if()

        num1 = num1/num2;
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mleft = new S_exp_tree();
        temp.mleft.mis_null = false;
        temp.mleft.mback = temp;
        temp.mleft.mtoken.add( Integer.toString( num1 ) );
        temp.mleft.mtype.add( "INT" );
        temp.mright = start.mright.mright;
        temp.mleft.mleft = new S_exp_tree();
        temp.mleft.mright = new S_exp_tree();
      } // else
    
      Check_num( start, "/" );
      return Divide( temp );
    } // if()
    else {
      throw new Incorrect_number_of_arguments( "/" );
    } // else


  } // Divide()

  S_exp_tree Not( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    if ( start.mis_null == false && start.mtype.isEmpty() == true ) {
      if ( start.mright.mis_null == false ) {
        throw new Incorrect_number_of_arguments( "not" );
      } // if()

      temp = EvalSExp( start.mleft );
      if ( temp.mtype.get( 0 ).equals( "T" ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else if ( temp.mtype.get( 0 ).equals( "NIL" ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // if()
    else if ( start.mis_null == false && start.mtype.isEmpty() == false ) {
      temp = EvalSExp( start );
      if ( temp.mtype.get( 0 ).equals( "T" ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else if ( temp.mtype.get( 0 ).equals( "NIL" ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // else if()
    else {
      throw new Incorrect_number_of_arguments( "not" ); 
    } // else


  } // Not()

  S_exp_tree And( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree left_bool;
    S_exp_tree right_bool;

    if ( start.mis_null == false && start.mtoken.isEmpty() == true ) {
      left_bool = Find_bool( start.mleft, "and" );
      mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );
      if ( start.mright.mis_null == false ) {
        right_bool = Find_bool( start.mright, "and" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );    
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          return start.mleft;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( "and" );
        } // else

      } // else

      if ( left_bool.mtype.get( 0 ).equals( "NIL" ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mleft = new S_exp_tree();
        temp.mleft.mis_null = false;
        temp.mleft.mback = temp;
        temp.mleft.mtoken.add( "nil" );
        temp.mleft.mtype.add( "NIL" );
        temp.mright = start.mright.mright;
        temp.mleft.mleft = new S_exp_tree();
        temp.mleft.mright = new S_exp_tree();
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mleft = right_bool;
        temp.mright = start.mright.mright;
        
      } // else

      return And( temp );
    } // if()
    else {
      throw new Incorrect_number_of_arguments( "and" );
    } // else


  } // And()

  S_exp_tree Or( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree left_bool;
    S_exp_tree right_bool;
    

    if ( start.mis_null == false && start.mtoken.isEmpty() == true ) {
      left_bool = Find_bool( start.mleft, "or" );
      mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );
      if ( start.mright.mis_null == false ) {
        right_bool = Find_bool( start.mright, "or" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );    
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          return start.mleft;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( "or" );
        } // else

      } // else

      if ( left_bool.mtype.get( 0 ).equals( "NIL" ) == false ) {
        return left_bool;
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mleft = right_bool;
        temp.mright = start.mright.mright;
      } // else
    
      return Or( temp );
    } // if()
    else {
      throw new Incorrect_number_of_arguments( "or" );
    } // else


  } // Or()

  S_exp_tree Greater( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree left_num;
    S_exp_tree right_num;
    int num1;
    double num1f;
    int num2;
    double num2f;
    if ( start.mis_null == false ) {
      if ( start.mleft.mis_null == false ) { // only one para
        left_num = Find_num( start.mleft, ">" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "#t" );
          temp.mtype.add( "T" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( ">" );
        } // else
        
      } // else

      if ( start.mright.mis_null == false ) {
        right_num = Find_num( start.mright, ">" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );    
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "#t" );
          temp.mtype.add( "T" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( ">" );
        } // else
  
      } // else
  
      Check_num( start, ">" );
      if ( left_num.mtype.get( 0 ).equals( "FLOAT" ) || right_num.mtype.get( 0 ).equals( "FLOAT" ) ) {
        num1f = Double.parseDouble( left_num.mtoken.get( 0 ) );
        num2f = Double.parseDouble( right_num.mtoken.get( 0 ) );
        if ( num1f > num2f ) {
          return Greater( start.mright );
        } // if()
        else {
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "nil" );
          temp.mtype.add( "NIL" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // else
      } // if()
      else {
        num1 = Integer.parseInt( left_num.mtoken.get( 0 ) );  
        num2 = Integer.parseInt( right_num.mtoken.get( 0 ) );  
        if ( num1 > num2 ) {
          return Greater( start.mright );
        } // if()
        else {
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "nil" );
          temp.mtype.add( "NIL" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // else
  
      } // else

    } // if()
    else {
      throw new Incorrect_number_of_arguments( ">" );
    } // else


  } // Greater()

  S_exp_tree Greater_equal( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree left_num;
    S_exp_tree right_num;
    int num1;
    double num1f;
    int num2;
    double num2f;
    
    if ( start.mis_null == false ) {
      if ( start.mleft.mis_null == false ) { // only one para
        left_num = Find_num( start.mleft, ">=" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "#t" );
          temp.mtype.add( "T" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( ">=" );
        } // else
        
      } // else

      if ( start.mright.mis_null == false ) {
        right_num = Find_num( start.mright, ">=" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );    
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "#t" );
          temp.mtype.add( "T" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( ">=" );
        } // else
  
      } // else
  
      Check_num( start, ">=" );
      if ( left_num.mtype.get( 0 ).equals( "FLOAT" ) || right_num.mtype.get( 0 ).equals( "FLOAT" ) ) {
        num1f = Double.parseDouble( left_num.mtoken.get( 0 ) );
        num2f = Double.parseDouble( right_num.mtoken.get( 0 ) );
        if ( num1f >= num2f ) {
          return Greater_equal( start.mright );
        } // if()
        else {
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "nil" );
          temp.mtype.add( "NIL" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // else
      } // if()
      else {
        num1 = Integer.parseInt( left_num.mtoken.get( 0 ) );  
        num2 = Integer.parseInt( right_num.mtoken.get( 0 ) );  
        if ( num1 >= num2 ) {
          return Greater_equal( start.mright );
        } // if()
        else {
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "nil" );
          temp.mtype.add( "NIL" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // else
  
      } // else

    } // if()
    else {
      throw new Incorrect_number_of_arguments( ">=" );
    } // else


  } // Greater_equal()

  S_exp_tree Less( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree left_num;
    S_exp_tree right_num;
    int num1;
    double num1f;
    int num2;
    double num2f;
    
    if ( start.mis_null == false ) {
      if ( start.mleft.mis_null == false ) { // only one para
        left_num = Find_num( start.mleft, "<" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "#t" );
          temp.mtype.add( "T" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( "<" );
        } // else
        
      } // else

      if ( start.mright.mis_null == false ) {
        right_num = Find_num( start.mright, "<" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );    
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "#t" );
          temp.mtype.add( "T" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( "<" );
        } // else
  
      } // else
  
      Check_num( start, "<" );
      if ( left_num.mtype.get( 0 ).equals( "FLOAT" ) || right_num.mtype.get( 0 ).equals( "FLOAT" ) ) {
        num1f = Double.parseDouble( left_num.mtoken.get( 0 ) );
        num2f = Double.parseDouble( right_num.mtoken.get( 0 ) );
        if ( num1f < num2f ) {
          return Less( start.mright );
        } // if()
        else {
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "nil" );
          temp.mtype.add( "NIL" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // else
      } // if()
      else {
        num1 = Integer.parseInt( left_num.mtoken.get( 0 ) );  
        num2 = Integer.parseInt( right_num.mtoken.get( 0 ) );  
        if ( num1 < num2 ) {
          return Less( start.mright );
        } // if()
        else {
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "nil" );
          temp.mtype.add( "NIL" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // else
  
      } // else

    } // if()
    else {
      throw new Incorrect_number_of_arguments( "<" );
    } // else


  } // Less()

  S_exp_tree Less_equal( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree left_num;
    S_exp_tree right_num;
    int num1;
    double num1f;
    int num2;
    double num2f;
    
    if ( start.mis_null == false ) {
      if ( start.mleft.mis_null == false ) { // only one para
        left_num = Find_num( start.mleft, "<=" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "#t" );
          temp.mtype.add( "T" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( "<=" );
        } // else
        
      } // else

      if ( start.mright.mis_null == false ) {
        right_num = Find_num( start.mright, "<=" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );    
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "#t" );
          temp.mtype.add( "T" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( "<=" );
        } // else
  
      } // else
  
      Check_num( start, "<=" );
      if ( left_num.mtype.get( 0 ).equals( "FLOAT" ) || right_num.mtype.get( 0 ).equals( "FLOAT" ) ) {
        num1f = Double.parseDouble( left_num.mtoken.get( 0 ) );
        num2f = Double.parseDouble( right_num.mtoken.get( 0 ) );
        if ( num1f <= num2f ) {
          return Less_equal( start.mright );
        } // if()
        else {
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "nil" );
          temp.mtype.add( "NIL" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // else
      } // if()
      else {
        num1 = Integer.parseInt( left_num.mtoken.get( 0 ) );  
        num2 = Integer.parseInt( right_num.mtoken.get( 0 ) );  
        if ( num1 <= num2 ) {
          return Less_equal( start.mright );
        } // if()
        else {
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "nil" );
          temp.mtype.add( "NIL" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // else
  
      } // else

    } // if()
    else {
      throw new Incorrect_number_of_arguments( "<=" );
    } // else


  } // Less_equal()

  S_exp_tree Two_num_equal( S_exp_tree start )  throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree left_num;
    S_exp_tree right_num;
    int num1;
    double num1f;
    int num2;
    double num2f;
    
    if ( start.mis_null == false ) {
      if ( start.mleft.mis_null == false ) { // only one para
        left_num = Find_num( start.mleft, "==" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "#t" );
          temp.mtype.add( "T" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( "==" );
        } // else
        
      } // else

      if ( start.mright.mis_null == false ) {
        right_num = Find_num( start.mright, "==" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );    
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "#t" );
          temp.mtype.add( "T" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( "==" );
        } // else
  
      } // else
  
      Check_num( start, "==" );
      if ( left_num.mtype.get( 0 ).equals( "FLOAT" ) || right_num.mtype.get( 0 ).equals( "FLOAT" ) ) {
        num1f = Double.parseDouble( left_num.mtoken.get( 0 ) );
        num2f = Double.parseDouble( right_num.mtoken.get( 0 ) );
        if ( num1f == num2f ) {
          return Two_num_equal( start.mright );
        } // if()
        else {
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "nil" );
          temp.mtype.add( "NIL" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // else
      } // if()
      else {
        num1 = Integer.parseInt( left_num.mtoken.get( 0 ) );  
        num2 = Integer.parseInt( right_num.mtoken.get( 0 ) );  
        if ( num1 == num2 ) {
          return Two_num_equal( start.mright );
        } // if()
        else {
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "nil" );
          temp.mtype.add( "NIL" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // else
  
      } // else  

    } // if()
    else {
      throw new Incorrect_number_of_arguments( "==" );
    } // else    

  } // Two_num_equal()

  S_exp_tree String_append( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree left_str;
    S_exp_tree right_str;
    String temp_str;

    
    if ( start.mis_null == false && start.mtoken.isEmpty() == true ) {
      left_str = Find_string( start.mleft, "string-append" );
      mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );
      if ( start.mright.mis_null == false ) {
        right_str = Find_string( start.mright, "string-append" );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );
      } // if()
      else {
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          return start.mleft;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( "string-append" );
        } // else
        
      } // else
 
      temp = new S_exp_tree();
      temp.mis_null = false;
      temp_str = left_str.mtoken.get( 0 ).substring( 0, left_str.mtoken.get( 0 ).length()-1 )
                 + right_str.mtoken.get( 0 ).substring( 1, right_str.mtoken.get( 0 ).length() );
      temp.mleft = new S_exp_tree();    
      temp.mleft.mis_null = false;       
      temp.mleft.mtoken.add( temp_str );
      temp.mleft.mtype.add( "STRING" );
      temp.mright = start.mright.mright;
      Check_string( start, "string-append" );
      return String_append( temp );

    } // if()
    else {
      throw new Incorrect_number_of_arguments( "string-append" );
    } // else


  } // String_append()

  S_exp_tree String_compare( S_exp_tree start, String option ) throws Non_list, 
  Incorrect_number_of_arguments, With_incorrect_argument_type, Attempt_to_apply_non_function, 
  No_return_value, Unbound_symbol, Division_by_zero, DEFINE_format, COND_format,
  Level_of_clean_enviroment, Level_of_define {
    S_exp_tree temp;
    S_exp_tree left_str;
    S_exp_tree right_str;
  
    if ( start.mis_null == false ) {
      if ( start.mleft.mis_null == true ) { // only one para
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "#t" );
          temp.mtype.add( "T" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( option );
        } // else

      } // if()
      else {
        left_str = Find_string( start.mleft, option );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );
      } // else
      
      if ( start.mright.mis_null == false ) {
        right_str = Find_string( start.mright, option );
        mpara_num.setElementAt( mpara_num.lastElement()+1, mpara_num.size()-1 );
      } // if()
      else { // only one para
        if ( mpara_num.lastElement() >= 2 ) {
          mpara_num.remove( mpara_num.size()-1 );
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "#t" );
          temp.mtype.add( "T" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
        } // if()
        else {
          throw new Incorrect_number_of_arguments( option );
        } // else

      } // else
 
      Check_string( start, option );
      if ( option.equals( "string>?" ) ) {
        if ( left_str.mtoken.get( 0 ).compareTo( right_str.mtoken.get( 0 ) ) > 0 ) {
          return String_compare( start.mright, option );
        } // if()
        else {
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "nil" );
          temp.mtype.add( "NIL" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;

        } // else

      } // if()
      else if ( option.equals( "string<?" ) ) {
        if ( left_str.mtoken.get( 0 ).compareTo( right_str.mtoken.get( 0 ) ) < 0 ) {
          return String_compare( start.mright, option );
        } // if()
        else {
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "nil" );
          temp.mtype.add( "NIL" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;

        } // else

      } // else if()
      else if ( option.equals( "string=?" ) ) {
        if ( left_str.mtoken.get( 0 ).compareTo( right_str.mtoken.get( 0 ) ) == 0 ) {          
          return String_compare( start.mright, option );
        } // if()
        else {
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "nil" );
          temp.mtype.add( "NIL" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;

        } // else
      } // else if()
      else { // error
        return new S_exp_tree();
      } // else

    } // if()
    else {
      throw new Incorrect_number_of_arguments( option );
    } // else


  } // String_compare()

  S_exp_tree Eqv( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree left;
    S_exp_tree right;
    int loc1;
    int loc2;

    if ( start.mis_null == false && start.mtoken.isEmpty() == true ) {
      left = start.mleft;
      if ( start.mright.mis_null == false && start.mright.mtoken.isEmpty() == false ) {
        right = start.mright;
      } // if()
      else if ( start.mright.mis_null == false && start.mright.mtoken.isEmpty() == true &&
                start.mright.mleft.mis_null == false && start.mright.mright.mis_null == true ) {
        right = start.mright.mleft;
      } // else if()
      else {
        throw new Incorrect_number_of_arguments( "eqv?" );
      } // else

      if ( left.mtype.isEmpty() == false &&
           right.mtype.isEmpty() == false &&
           ( left.mtype.get( 0 ).equals( "INT" ) || left.mtype.get( 0 ).equals( "String" ) || 
             left.mtype.get( 0 ).equals( "NIL" ) || left.mtype.get( 0 ).equals( "T" ) || 
             left.mtype.get( 0 ).equals( "FLOAT" ) || left.mtype.get( 0 ).equals( "SYMBOL" ) ) &&
           ( right.mtype.get( 0 ).equals( "INT" ) || right.mtype.get( 0 ).equals( "String" ) || 
             right.mtype.get( 0 ).equals( "NIL" ) || right.mtype.get( 0 ).equals( "T" ) || 
             right.mtype.get( 0 ).equals( "FLOAT" ) || right.mtype.get( 0 ).equals( "SYMBOL" ) ) ) {

        if ( left.mtype.isEmpty() == false &&
             left.mtype.get( 0 ).equals( "SYMBOL" ) &&
             right.mtype.isEmpty() == false &&
             right.mtype.get( 0 ).equals( "SYMBOL" ) ) {
 
          loc1 = Find_table( left.mtoken.get( 0 ) );
          loc2 = Find_table( right.mtoken.get( 0 ) );
          if ( mtabel.maddress.get( loc1 ) == mtabel.maddress.get( loc2 ) ) {
            temp = new S_exp_tree();
            temp.mis_null = false;
            temp.mtoken.add( "#t" );
            temp.mtype.add( "T" );
            temp.mleft = new S_exp_tree();
            temp.mright = new S_exp_tree();
            return temp;
          } // if()
          else {
            temp = new S_exp_tree();
            temp.mis_null = false;
            temp.mtoken.add( "nil" );
            temp.mtype.add( "NIL" );
            temp.mleft = new S_exp_tree();
            temp.mright = new S_exp_tree();
            return temp;
          } // else


        } // if()
        else if ( Equal_string( left ).mtype.get( 0 ).equals( "T" ) &&
                  Equal_string( right ).mtype.get( 0 ).equals( "T" ) ) {
          temp = new S_exp_tree();
          temp.mis_null = false;
          temp.mtoken.add( "nil" );
          temp.mtype.add( "NIL" );
          temp.mleft = new S_exp_tree();
          temp.mright = new S_exp_tree();
          return temp;
          // System.out.print( "不知道" );
        } // else if()
        else {
          return Equal( start );
        } // else

      } // if()
      else if ( EvalSExp( left ).mtype.get( 0 ).equals( "NIL" ) &&
                EvalSExp( right ).mtype.get( 0 ).equals( "NIL" ) ) {

        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;

      } // else if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // else

    } // if()
    else {
      throw new Incorrect_number_of_arguments( "eqv?" );
    } // else


  } // Eqv()

  S_exp_tree Equal( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree left;
    S_exp_tree right;

    if ( start.mis_null == false && start.mtoken.isEmpty() == true ) {
      left = start.mleft;
      if ( start.mright.mis_null == false && start.mright.mtoken.isEmpty() == false ) {
        right = start.mright;
      } // if()
      else if ( start.mright.mis_null == false && start.mright.mtoken.isEmpty() == true &&
                start.mright.mleft.mis_null == false && start.mright.mright.mis_null == true ) {
        right = start.mright.mleft;
      } // else if()
      else {
        throw new Incorrect_number_of_arguments( "equal?" );
      } // else

      if ( left.mtype.isEmpty() == false ) {
        left = EvalSExp( left );
      } // if()

      if ( right.mtype.isEmpty() == false ) {
        right = EvalSExp( right );
      } // if()

      if ( Compare_S_exp( left, right ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "#t" );
        temp.mtype.add( "T" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;
      } // if()
      else {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "nil" );
        temp.mtype.add( "NIL" );
        temp.mleft = new S_exp_tree();
        temp.mright = new S_exp_tree();
        return temp;

      } // else

    } // if()
    else {
      throw new Incorrect_number_of_arguments( "equal?" );
    } // else


  } // Equal()

  boolean Compare_S_exp( S_exp_tree left, S_exp_tree right ) {
    if ( left.mis_null == true && right.mis_null == true ) {
      if ( left.mtoken.equals( right.mtoken ) ) { // 不比type 因為( 3 ( 4 5 ) ) 有ATOM
        return true;
      } // if()
      else {
        return false;
      } // else

    } // if()
    else if ( left.mis_null != right.mis_null ) {
      return false;
    } // else if()
    else { // all false
      if ( left.mtoken.equals( right.mtoken ) && left.mtype.equals( right.mtype ) ) {
        if ( Compare_S_exp( left.mleft, right.mleft ) && 
             Compare_S_exp( left.mright, right.mright ) ) {
          return true;
        } // if()
        else if ( Compare_S_exp( left.mleft, right.mleft ) == true && 
                  Compare_S_exp( left.mright, right.mright ) == false ) {

          if ( left.mright.mis_null == true && right.mright.mis_null == false &&
               right.mright.mtype.size() == 2 && right.mright.mtype.get( 0 ).equals( "DOT" ) &&
               right.mright.mtype.get( 1 ).equals( "NIL" ) ) {
            return true;
          } // if()
          else if ( left.mright.mis_null == false && left.mright.mtype.size() == 2 && 
                    left.mright.mtype.get( 0 ).equals( "DOT" ) && left.mright.mtype.get( 1 ).equals( "NIL" ) && 
                    right.mright.mis_null == true ) {
            return true;
          } // else if()
          else {
            return false;
          } // else
          
        } // else if()
        else {
          return false;
        } // else

      } // if()
      else {
        return false;
      } // else

    } // else

  } // Compare_S_exp()

  S_exp_tree Begin( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree walk;
    if ( start.mis_null == false && start.mtype.isEmpty() == true ) {
      walk = start;
      while ( walk.mright.mis_null != true ) {
        walk = walk.mright;
      } // while()

      if ( walk.mtoken.isEmpty() == false ) {
        return EvalSExp( walk );
      } // if()
      else {
        return EvalSExp( walk.mleft );
      } // else
      

    } // if()
    else if ( start.mis_null == false && start.mtype.isEmpty() == false ) {
      return EvalSExp( start );
    } // else if()
    else {
      throw new Incorrect_number_of_arguments( "begin" ); 
    } // else


  } // Begin()

  S_exp_tree Iff( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    if ( start.mis_null == false && start.mtype.isEmpty() == true ) {
      if ( Condition( start.mleft, false ) ) {
        if ( start.mright.mis_null == false ) {
          if ( start.mright.mright.mis_null == false && 
               start.mright.mright.mright.mis_null == false ) {
            throw new Incorrect_number_of_arguments( "if" );
          } // if()

          if ( start.mright.mtoken.isEmpty() == false ) {
            return EvalSExp( start.mright );
          } // if()
          else if ( start.mright.mleft.mtoken.isEmpty() == false ) {
            return EvalSExp( start.mright.mleft );
          } // else if()
          else {
            throw new No_return_value( "error" );
          } // else

        } // if()
        else {
          throw new No_return_value( "error" );
        } // else
        
      } // if()
      else {
        if ( start.mright.mis_null == false && start.mright.mright.mis_null == false ) {
          if ( start.mright.mright.mright.mis_null == false ) {
            throw new Incorrect_number_of_arguments( "if" );
          } // if()

          if ( start.mright.mright.mtoken.isEmpty() == false ) {
            return EvalSExp( start.mright.mright );
          } // if()
          else if ( start.mright.mright.mleft.mtoken.isEmpty() == false ) {
            return EvalSExp( start.mright.mright.mleft );
          } // else if()
          else {
            throw new No_return_value( "error" );
          } // else
          
        } // if()
        else {
          throw new No_return_value( "error" );
        } // else
      } // else

    } // if()
    else {
      throw new Incorrect_number_of_arguments( "if" );
    } // else 

  } // Iff()

  S_exp_tree Cond( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    Check_cond( start );
    if ( start.mis_null == false && start.mtype.isEmpty() == false && 
         start.mtype.get( 0 ).equals( "LEFT-PAREN" ) ) {
      if ( Condition( start.mleft, true ) ) {

        if ( start.mright.mis_null == false && start.mright.mtoken.isEmpty() == false ) {
          return EvalSExp( start.mright );
        } // if()
        else if ( start.mright.mis_null == false && start.mright.mleft.mis_null == false &&
                  start.mright.mleft.mtoken.isEmpty() == false ) {
          temp = start.mright;
          while ( temp.mright.mis_null != true ) {
            temp = temp.mright;
          } // while()

          if ( temp.mtoken.isEmpty() == false ) {
            return EvalSExp( temp );
          } // if()
          else {
            return EvalSExp( temp.mleft );
          } // else
          
        } // else if()
        else {
          throw new COND_format( "error" );
        } // else
        
      } // if()
      else {
        throw new COND_format( "error" );
      } // else
    } // if()
    else if ( start.mis_null == false && start.mtype.isEmpty() == true && 
              start.mleft.mtype.isEmpty() == false && 
              start.mleft.mtype.get( 0 ).equals( "LEFT-PAREN" ) ) {
      if ( Condition( start.mleft.mleft, true ) ) {
        
        if ( start.mleft.mright.mis_null == false && start.mleft.mright.mtoken.isEmpty() == false ) {
          return EvalSExp( start.mleft.mright );
        } // if()
        else if ( start.mleft.mright.mis_null == false && start.mleft.mright.mleft.mis_null == false &&
                  start.mleft.mright.mleft.mtoken.isEmpty() == false ) {
          temp = start.mleft.mright;
          while ( temp.mright.mis_null != true ) {
            temp = temp.mright;
          } // while()

          if ( temp.mtoken.isEmpty() == false ) {
            return EvalSExp( temp );
          } // if()
          else {
            return EvalSExp( temp.mleft );
          } // else
          
        } // else if()
        else {
          throw new COND_format( "error" );
        } // else

      } // if()
      else {
        return Cond( start.mright );
      } // else

    } // else if()
    else if ( start.mis_null == true ) { // no else
      throw new No_return_value( "error" );
    } // else if()
    else { // no left paren
      throw new COND_format( "error" );
    } // else

  } // Cond()

  boolean Condition( S_exp_tree start, boolean has_else ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp ;
    int loc;
    if ( has_else == true ) {
      if ( start.mback.mback.mright.mis_null == true ) {
        ;
      } // if()
      else {
        has_else = false;
      } // else

    } // if()

    if ( start.mtype.get( 0 ).equals( "LEFT-PAREN" ) ) {
      temp = EvalSExp( start );
    } // if()
    else if ( start.mtoken.get( 0 ).equals( "else" ) ) {
      if ( has_else == true ) {
        return true;
      } // if()
      else {
        loc = Find_table( "else" );
        if ( loc == -1 ) {
          throw new Unbound_symbol( "else" );
        } // if()
        else {
          temp = mtabel.maddress.get( loc );
        } // else
        
      } // else

    } // else if()
    else if ( start.mtype.get( 0 ).equals( "SYMBOL" ) ) {
      loc = Find_table( start.mtoken.get( 0 ) );
      if ( loc == -1 ) {
        throw new Unbound_symbol( start.mtoken.get( 0 ) );
      } // if()
      else {
        temp = mtabel.maddress.get( loc );
      } // else

    } // else if()
    else {
      temp = start;
    } // else

    if ( temp.mtype.get( 0 ).equals( "T" ) ) {
      return true;
    } // if()
    else if ( temp.mtype.get( 0 ).equals( "NIL" ) ) {
      return false;
    } // else if()
    else {
      return true;
    } // else

  } // Condition()

  void Check_cond( S_exp_tree start ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    S_exp_tree temp;
    S_exp_tree walk;
    walk = start;
    while( walk.mis_null != true ) {
      if ( walk.mis_null == false && walk.mtype.isEmpty() == false && 
           walk.mtype.get( 0 ).equals( "LEFT-PAREN" ) ) {
        if ( walk.mright.mis_null == false && walk.mright.mtoken.isEmpty() == false ) {
          ;
        } // if()
        else if ( walk.mright.mis_null == false && walk.mright.mleft.mis_null == false &&
                  walk.mright.mleft.mtoken.isEmpty() == false ) {
          ;          
        } // else if()
        else {
          throw new COND_format( "error" );
        } // else

      } // if()
      else if ( walk.mis_null == false && walk.mtype.isEmpty() == true && 
                walk.mleft.mtype.isEmpty() == false && 
                walk.mleft.mtype.get( 0 ).equals( "LEFT-PAREN" ) ) {
        if ( walk.mleft.mright.mis_null == false && walk.mleft.mright.mtoken.isEmpty() == false ) {
          ;
        } // if()
        else if ( walk.mleft.mright.mis_null == false && walk.mleft.mright.mleft.mis_null == false &&
                  walk.mleft.mright.mleft.mtoken.isEmpty() == false ) {
          ;
          
        } // else if()
        else {
          throw new COND_format( "error" );
        } // else

      } // else if()
      else { // no left paren
        throw new COND_format( "error" );
      } // else

      walk = walk.mright;
      
    } // while()


  } // Check_cond()

  S_exp_tree Clean_environment( S_exp_tree start ) {
    
    mtabel.msymbol.clear();
    mtabel.maddress.clear();
    System.out.println( "environment cleaned" );
    return new S_exp_tree();

  } // Clean_environment()

  int Find_table( String token ) {
    int i = 0;
    boolean find = false;
    int loc = -1;
    while ( ( i < mtabel.msymbol.size() ) && !find ) {
      if ( mtabel.msymbol.get( i ).equals( token ) ) {
        loc = i;
        find = true;
      } // if()

      i++;
    } // while()

    return loc;

  } // Find_table()

  S_exp_tree Function_call( S_exp_tree start, boolean is_head ) throws Non_list, 
  Incorrect_number_of_arguments, With_incorrect_argument_type, 
  Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format,
  Level_of_clean_enviroment, Level_of_define {
    S_exp_tree temp;
    int loc;
    String str;
    if (  start.mleft.mtoken.get( 0 ).equals( "define" ) ) {
      if ( is_head == true ) {
        return Define( start.mright );
      } // if()
      else {
        throw new Level_of_define( "" );
      } // else
      
    } // if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "cons" ) ) {
      return Cons( start.mright, is_head );
    } // if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "list" ) ) {
      return List( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "\'" ) || start.mleft.mtoken.get( 0 ).equals( "quote" ) ) {
      if ( start.mleft.mtoken.get( 0 ).equals( "\'" ) ) {
        return Quote( start );
      } // if()
      else {
        return Quote( start.mright );
      } // else
      
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "car" ) ) {
      return Car( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "cdr" ) ) {
      return Cdr( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "atom?" ) ) {
      return Equal_atom( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "pair?" ) ) {
      return Equal_pair( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "list?" ) ) {
      return Equal_list( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "null?" ) ) {
      return Equal_null( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "integer?" ) ) {
      return Equal_int( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "real?" ) ) {
      return Equal_real( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "number?" ) ) {
      return Equal_number( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "string?" ) ) {
      return Equal_string( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "boolean?" ) ) {
      return Equal_boolean( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "symbol?" ) ) {
      return Equal_symbol( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "+" ) ) {
      mpara_num.add( new Integer( 0 ) );
      return Add( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "-" ) ) {
      mpara_num.add( new Integer( 0 ) );
      return Subtract( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "*" ) ) {
      mpara_num.add( new Integer( 0 ) );
      return Multiply( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "/" ) ) {
      mpara_num.add( new Integer( 0 ) );
      return Divide( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "not" ) ) {
      return Not( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "and" ) ) {
      mpara_num.add( new Integer( 0 ) );
      return And( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "or" ) ) {
      mpara_num.add( new Integer( 0 ) );
      return Or( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( ">" ) ) {
      mpara_num.add( new Integer( 0 ) );
      return Greater( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( ">=" ) ) {
      mpara_num.add( new Integer( 0 ) );
      return Greater_equal( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "<" ) ) {
      mpara_num.add( new Integer( 0 ) );
      return Less( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "<=" ) ) {
      mpara_num.add( new Integer( 0 ) );
      return Less_equal( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "=" ) ) {
      mpara_num.add( new Integer( 0 ) );
      return Two_num_equal( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "string-append" ) ) {
      mpara_num.add( new Integer( 0 ) );
      return String_append( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "string>?" ) ) {
      mpara_num.add( new Integer( 0 ) );
      return String_compare( start.mright, "string>?" );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "string<?" ) ) {
      mpara_num.add( new Integer( 0 ) );
      return String_compare( start.mright, "string<?" );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "string=?" ) ) {
      mpara_num.add( new Integer( 0 ) );
      return String_compare( start.mright, "string=?" );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "eqv?" ) ) {
      return Eqv( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "equal?" ) ) {
      return Equal( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "begin" ) ) {  
      return Begin( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "if" ) ) {
      return Iff( start.mright );
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "cond" ) ) {
      if ( start.mright.mis_null == true ) {
        throw new COND_format( "error" );
      } // if()
      else {
        return Cond( start.mright );
      } // else
      
    } // else if()
    else if ( start.mleft.mtoken.get( 0 ).equals( "clean-environment" ) ) {
      if ( is_head == true ) {
        return Clean_environment( start.mright );
      } // if()
      else {
        throw new Level_of_clean_enviroment( "" );
      } // else
      
    } // else if()
    else if ( start.mleft.mtype.get( 0 ).equals( "LEFT-PAREN" ) ) {
      temp = new S_exp_tree();
      temp.mis_null = false;
      temp.mtoken.add( "(" );
      temp.mtype.add( "LEFT-PAREN" );
      if ( start.mleft.mleft.mis_null == false && start.mleft.mleft.mtoken.isEmpty() == false ) {
        temp.mleft = Function_call( start.mleft, is_head );
      } // if()
      else if ( start.mleft.mleft.mis_null == false && start.mleft.mleft.mtoken.isEmpty() == true &&
                start.mleft.mleft.mleft.mis_null == false ) {
        temp.mleft = Function_call( start.mleft.mleft, is_head );
      } // else if()
      else {
        throw new Attempt_to_apply_non_function( "(" );
      } // else

      temp.mright = start.mright;
      if ( temp.mleft.mtoken.get( 0 ).length() > 13 ) {
        str = temp.mleft.mtoken.get( 0 ).substring( 12, temp.mleft.mtoken.get( 0 ).length()-1 );
        temp.mleft = new S_exp_tree();
        temp.mleft.mis_null = false;
        temp.mleft.mtoken.add( str );
        temp.mleft.mtype.add( "SYMBOL" );
        temp.mleft.mleft = new S_exp_tree();
        temp.mleft.mright = new S_exp_tree();
      } // if()
      else {
        throw new Attempt_to_apply_non_function( start.mleft.mtoken.get( 0 ) );
      } // else      

      return Function_call( temp, is_head );
    } // else if()
    else if ( start.mleft.mtype.get( 0 ).equals( "SYMBOL" ) ) {
      loc = Find_table( start.mleft.mtoken.get( 0 ) );
      if ( loc != -1 ) {
        str = mtabel.maddress.get( loc ).mtoken.get( mtabel.maddress.get( loc ).mtoken.size()-1 );
        if ( str.length() > 13 ) {
          str = str.substring( 12, str.length()-1 );
        } // if()
        else {
          throw new Attempt_to_apply_non_function( start.mleft.mtoken.get( 0 ) );
        } // else        

      } // if()
      else {
        throw new Unbound_symbol( start.mleft.mtoken.get( 0 ) );
      } // else

      if ( Is_primitive( str ) ) {
        temp = new S_exp_tree();
        temp.mis_null = false;
        temp.mtoken.add( "(" );
        temp.mtype.add( "LEFT-PAREN" );
        temp.mleft = new S_exp_tree();
        temp.mleft.mis_null = false;
        temp.mleft.mtoken.add( str );
        temp.mleft.mtype.add( "SYMBOL" );
        temp.mleft.mleft = new S_exp_tree();
        temp.mleft.mright = new S_exp_tree();
        temp.mright = start.mright;
        return Function_call( temp, is_head );
      } // if()
      else {
        throw new Attempt_to_apply_non_function( start.mleft.mtoken.get( 0 ) );
      } // else
      


    } // else if()
    else {
      throw new Attempt_to_apply_non_function( start.mleft.mtoken.get( 0 ) );
    } // else

  } // Function_call()

  S_exp_tree EvalSExp() {
    int loc = 0;
    // 從根開始
    try {
      if ( Is_exit() ) {
        return msexp;
      } // if()

    } // try
    catch ( Incorrect_number_of_arguments ex ) {
      mhas_error = true;
      System.out.print( "ERROR (incorrect number of arguments) : " );
      System.out.println( ex.Get_error_token() );
      return new S_exp_tree();
    } // catch
    
    
    if ( msexp.mtoken.get( 0 ).equals( "(" ) ) {
      try {
        return Function_call( msexp, true );  
      } // try
      catch ( Non_list ex ) { // 
        mhas_error = true;
        System.out.print( "ERROR (non-list) : " );
        msexp.mtype.add( 0, "error" );
        return new S_exp_tree();
      } // catch 
      catch ( Incorrect_number_of_arguments ex ) { // 
        mhas_error = true;
        System.out.print( "ERROR (incorrect number of arguments) : " );
        System.out.println( ex.Get_error_token() );
        return new S_exp_tree();
      } // catch 
      catch ( With_incorrect_argument_type ex ) { // 
        mhas_error = true;
        System.out.print( " with incorrect argument type) : " );
        PrintSExp( ex.Get_error_part() );
        return new S_exp_tree();
      } // catch 
      catch ( Attempt_to_apply_non_function ex ) { // 
        mhas_error = true;
        System.out.print( "ERROR (attempt to apply non-function) : " );
        System.out.println( ex.Get_error_token() );
        return new S_exp_tree();
      } // catch 
      catch ( No_return_value ex ) { // 
        mhas_error = true;
        System.out.print( "ERROR (no return value) : " );
        msexp.mtype.add( 0, "error" );
        return new S_exp_tree();
      } // catch 
      catch ( Unbound_symbol ex ) { // 
        mhas_error = true;
        System.out.print( "ERROR (unbound symbol) : " );
        System.out.println( ex.Get_error_token() );
        return new S_exp_tree();
      } // catch 
      catch ( Division_by_zero ex ) { // 
        mhas_error = true;
        System.out.print( "ERROR (division by zero) : " );
        System.out.println( "/" );
        return new S_exp_tree();
      } // catch 
      catch ( DEFINE_format ex ) { // 
        mhas_error = true;
        System.out.print( "ERROR (DEFINE format) : " );
        msexp.mtype.add( 0, "error" );
        return new S_exp_tree();
      } // catch 
      catch ( COND_format ex ) { // 
        mhas_error = true;
        System.out.print( "ERROR (COND format) : " );
        msexp.mtype.add( 0, "error" );
        return new S_exp_tree();
      } // catch 
      catch ( Level_of_clean_enviroment ex ) {
        mhas_error = true;
        System.out.println( "ERROR (level of CLEAN-ENVIRONMENT)" );
        return new S_exp_tree();
      } // catch
      catch ( Level_of_define ex ) {
        mhas_error = true;
        System.out.println( "ERROR (level of DEFINE)" );
        return new S_exp_tree();
      } // catch
      

    } // if()
    else if ( msexp.mtoken.get( 0 ).equals( "\'" ) ) {
      try {
        return Quote( msexp );
      } catch ( Incorrect_number_of_arguments ex ) { // 
        mhas_error = true;
        System.out.print( "ERROR (incorrect number of arguments) : " );
        System.out.println( ex.Get_error_token() );
        return new S_exp_tree();
      } // catch 
    } // else if()
    else if ( Is_primitive( msexp.mtoken.get( 0 ) ) ) { // primitive
      return Primitive( msexp.mtoken.get( 0 ) );
    } // else if()
    else if ( msexp.mtype.get( 0 ).equals( "SYMBOL" ) ) { // symbol
      loc = Find_table( msexp.mtoken.get( 0 ) );
      if ( loc == -1 ) {
        mhas_error = true;
        System.out.print( "ERROR (unbound symbol) : " );
        System.out.println( msexp.mtoken.get( 0 ) );
        return new S_exp_tree();
      } // if()
      else {
        return mtabel.maddress.get( loc );
      } // else

    } // else if()
    else {
      return msexp;
    } // else
    

  } // EvalSExp()

  S_exp_tree EvalSExp( S_exp_tree part ) throws Non_list, Incorrect_number_of_arguments,
  With_incorrect_argument_type, Attempt_to_apply_non_function, No_return_value,
  Unbound_symbol, Division_by_zero, DEFINE_format, COND_format, Level_of_clean_enviroment,
  Level_of_define {
    int loc;
    // System.out.print( part.mis_null );
    if ( part.mtoken.get( 0 ).equals( "(" ) ) {
      return Function_call( part, false );  
    } // if()
    else if ( part.mtoken.get( 0 ).equals( "\'" ) ) {
      try {
        return Quote( part );
      } catch ( Incorrect_number_of_arguments ex ) { // 
        throw ex;
      } // catch
      
    } // else if()
    else if ( Is_primitive( part.mtoken.get( 0 ) ) ) { // primitive
      return Primitive( part.mtoken.get( 0 ) );
    } // else if()
    else if ( part.mtype.get( 0 ).equals( "SYMBOL" ) ) { // symbol
      loc = Find_table( part.mtoken.get( 0 ) );
      if ( loc == -1 ) {
        mhas_error = true;
        throw new Unbound_symbol( part.mtoken.get( 0 ) );
      } // if()
      else {
        return mtabel.maddress.get( loc );
      } // else

    } // else if 
    else {
      return part;
    } // else

  } // EvalSExp()

  boolean Is_exit() throws Incorrect_number_of_arguments {

    if ( msexp.mtype.get( 0 ).equals( "LEFT-PAREN" ) && msexp.mleft.mtoken.get( 0 ).equals( "exit" )
         &&  msexp.mleft.mright.mis_null == true &&  msexp.mleft.mleft.mis_null == true 
         &&  msexp.mright.mis_null == true ) {

      return true;
    } // if()
    else if ( msexp.mtype.get( 0 ).equals( "LEFT-PAREN" ) && msexp.mleft.mtoken.get( 0 ).equals( "exit" )
              &&  msexp.mleft.mright.mis_null == true &&  msexp.mleft.mleft.mis_null == true
              &&  msexp.mright.mis_null == false && msexp.mright.mtype.isEmpty() == false
              && msexp.mright.mtype.get( 0 ).equals( "NIL" ) &&  msexp.mright.mleft.mis_null == true 
              &&  msexp.mright.mright.mis_null == true ) {
              // ( exit . nil ) would not come in
      return true;
    } // else if()
    else if ( msexp.mtype.get( 0 ).equals( "LEFT-PAREN" ) && msexp.mleft.mtoken.get( 0 ).equals( "exit" ) ) {
              // ( exit . nil ) would not come in
      throw new Incorrect_number_of_arguments( "exit" );
    } // else if()
    else {
      
      return false;

    } // else

  } // Is_exit()

  boolean No_eval_error() {
    return !mhas_error;
  } // No_eval_error()

  void Indent( String type ) {
    int count = 0;

    if ( type.equals( "RIGHT-PAREN" ) && mspace_num > 0 ) {
      mspace_num = mspace_num-2;
    } // if()

    while ( mfirst_atom && count < mspace_num ) {
      System.out.print( " " );
      count++;
    } // while()

    if ( type.equals( "ATOM" ) ) {
      mfirst_atom = true;
    } // if()
    else if ( type.equals( "LEFT-PAREN" ) ) {
      mspace_num = mspace_num+2;
      mfirst_atom = false;
    } // else if()
    else if ( type.equals( "QUOTE" ) ) {
      mspace_num = mspace_num+2;
      mfirst_atom = true;
    } // else if()
    else {
      ;
    } // else()

  } // Indent()

  void Prety_print( S_exp_tree current, boolean is_left ) {
    if ( current.mis_null == true ) { // return

    } // if()
    else if ( current.mtoken.isEmpty() ) {
      ;
    } // else if()
    else {
      int count = 0;
      while ( count < current.mtoken.size() ) { // print content
        if ( current.mtype.get( count ) == "INT" || current.mtype.get( count ) == "STRING" 
             || current.mtype.get( count ) == "FLOAT" || current.mtype.get( count ) == "T" 
             || current.mtype.get( count ) == "SYMBOL" ) {
          Indent( "ATOM" );
          System.out.println( current.mtoken.get( count ) );
        } // if()
        else if ( current.mtype.get( count ) == "QUOTE" ) {
          Indent( "QUOTE" );
          System.out.println( "( quote" );
        } // else if()
        else if ( is_left && current.mtype.get( count ) == "LEFT-PAREN" ) { // print (
          Indent( "LEFT-PAREN" );
          System.out.print( current.mtoken.get( count ) );
          System.out.print( " " ); 
        } // else if
        else if ( is_left && current.mtype.get( count ) == "NIL" ) { // print nil
          Indent( "ATOM" );
          System.out.println( current.mtoken.get( count ) );
        } // else if
        else if ( !is_left && current.mtype.get( count ) == "NIL" && 
                  ( count == 0 || current.mtype.get( count-1 ) != "DOT" ) ) {
          Indent( "ATOM" );
          System.out.println( current.mtoken.get( count ) );
        } // else if()
        else {

        } // else
        
        count++;

      } // while()

    } // else  

    if ( current.mleft.mis_null == false ) { // call left sexp
      if ( current.mleft.mtype.isEmpty() ) {
        Prety_print( current.mleft, true );
      } // if()
      else {
        Prety_print( current.mleft, true );
      } // else

    } // if()

    if ( current.mright.mis_null == false ) { // call right sexp
      if ( current.mright.mtype.isEmpty() ) {
        ;
      } // if()
      else if ( current.mright.mtype.get( 0 ) == "DOT" && 
                current.mright.mtype.get( 1 ) != "NIL" && 
                current.mright.mtype.get( 1 ) != "LEFT-PAREN" ) {
        Indent( "ATOM" );
        System.out.println( current.mright.mtoken.get( 0 ) );
      } // else if
      else if ( current.mtype.size() == 2 && current.mtype.get( 1 ).equals( "CONS" )
                && current.mright.mtype.get( 0 ) != "NIL" && 
                current.mright.mtype.get( 0 ) != "LEFT-PAREN" ) {
        Indent( "ATOM" );
        System.out.println( "." );
      } // else if()
      else {
        ;
      } // else
         
      if ( current.mtype.size() == 2 && current.mtype.get( 1 ).equals( "CONS" )
           && current.mright.mtype.isEmpty() == false && current.mright.mtype.get( 0 ) == "NIL" ) {
        ;
      } // if()
      else {
        Prety_print( current.mright, false );
      } // else
      

    } // if()

    if ( !current.mtoken.isEmpty() &&  ( current.mtype.get( 0 ).equals( "QUOTE" ) || 
                                         current.mtype.get( 0 ).equals( "LEFT-PAREN" ) ) ) { // print )
      int count = 0;
      while ( count < current.mtoken.size() ) { // print quote
        if ( current.mtype.get( count ) == "QUOTE" ) {
          Indent( "RIGHT-PAREN" );
          System.out.println( ")" );
        } // if()
        else if ( current.mtype.get( count ) == "LEFT-PAREN" && is_left ) {
          Indent( "RIGHT-PAREN" );
          System.out.println( ")" );
        } // else if()
        else if ( !is_left && current.mtype.get( count ) == "LEFT-PAREN" &&
                  current.mright.mis_null == true && current.mleft.mis_null == false 
                  && current.mleft.mleft.mis_null == false ) { // print ( prevent (cons 3 (4) )
          Indent( "RIGHT-PAREN" );
          System.out.println( ")" );
        } // else if()
                
        count++;

      } // while()

    } // if ()

  } // Prety_print()

  void PrintSExp() { 
    if ( msexp.mis_null == true ) {
      ;
    } // if ()
    else if ( msexp.mtype.get( 0 ).equals( "SYMBOL" ) || msexp.mtype.get( 0 ).equals( "INT" ) 
              || msexp.mtype.get( 0 ).equals( "FLOAT" ) || msexp.mtype.get( 0 ).equals( "STRING" ) 
              || msexp.mtype.get( 0 ).equals( "NIL" ) || msexp.mtype.get( 0 ).equals( "T" ) ) {

      System.out.println( msexp.mtoken.get( 0 ) );
    } // else if 
    else if ( msexp.mtype.get( 0 ).equals( "LEFT-PAREN" ) && msexp.mleft.mtoken.get( 0 ).equals( "exit" )
              &&  msexp.mleft.mright.mis_null == true &&  msexp.mleft.mleft.mis_null == true 
              &&  msexp.mright.mis_null == true ) {

      System.out.println( "" );
      mexit = "(exit)";
    } // else if()
    else if ( msexp.mtype.get( 0 ).equals( "LEFT-PAREN" ) && msexp.mleft.mtoken.get( 0 ).equals( "exit" )
              &&  msexp.mleft.mright.mis_null == true &&  msexp.mleft.mleft.mis_null == true
              &&  msexp.mright.mis_null == false && msexp.mright.mtype.isEmpty() == false
              && msexp.mright.mtype.get( 0 ).equals( "NIL" ) &&  msexp.mright.mleft.mis_null == true 
              &&  msexp.mright.mright.mis_null == true ) {
              // ( exit . nil ) would not come in
      System.out.println( "" );
      mexit = "(exit)";
    } // else if()
    else {
      
      Prety_print( msexp, true );

    } // else
    
  } // PrintSExp()

  void PrintSExp( S_exp_tree root ) { 
    if ( root.mis_null == true ) {
      ;
    } // if ()
    else if ( root.mtype.get( 0 ).equals( "SYMBOL" ) || root.mtype.get( 0 ).equals( "INT" ) 
              || root.mtype.get( 0 ).equals( "FLOAT" ) || root.mtype.get( 0 ).equals( "STRING" ) 
              || root.mtype.get( 0 ).equals( "NIL" ) || root.mtype.get( 0 ).equals( "T" ) ) {

      System.out.println( root.mtoken.get( 0 ) );
    } // else if 
    else if ( root.mtype.get( 0 ).equals( "LEFT-PAREN" ) && root.mleft.mtoken.get( 0 ).equals( "exit" )
              &&  root.mleft.mright.mis_null == true &&  root.mleft.mleft.mis_null == true 
              &&  root.mright.mis_null == true ) {

      System.out.println( "" );
      mexit = "(exit)";
    } // else if()
    else if ( root.mtype.get( 0 ).equals( "LEFT-PAREN" ) && root.mleft.mtoken.get( 0 ).equals( "exit" )
              &&  root.mleft.mright.mis_null == true &&  root.mleft.mleft.mis_null == true
              &&  root.mright.mis_null == false && root.mright.mtype.isEmpty() == false
              && root.mright.mtype.get( 0 ).equals( "NIL" ) &&  root.mright.mleft.mis_null == true 
              &&  root.mright.mright.mis_null == true ) {
              // ( exit . nil ) would not come in
      System.out.println( "" );
      mexit = "(exit)";
    } // else if()
    else {
      
      Prety_print( root, true );

    } // else
    
  } // PrintSExp()

  void PrintErrorMessage() {
    System.out.println( msexp.mtoken.get( 0 ) );
  } // PrintErrorMessage()

  void PrintEvalErrorMessage() {
    if ( msexp.mtype.get( 0 ).equals( "error" ) ) {
      msexp.mtype.remove( 0 );
      PrintSExp();
    } // if()

  } // PrintEvalErrorMessage()

} // class Scheme

class Main{
  public static void main( String [] argv ) {
    S_exp_tree result;
    Read r = new Read( 0 );
    Scheme ourScheme = new Scheme();
    System.out.println( "Welcome to OurScheme!" );
    while ( ourScheme.No_end() ) { 
      System.out.print( "\n" );
      System.out.print( "> " );
      ourScheme.ReadSExp(); 
      if ( ourScheme.No_error() ) {
        result = ourScheme.EvalSExp();
        if ( ourScheme.No_eval_error() ) { 
          ourScheme.PrintSExp( result );
        } // if()
        else {
          ourScheme.PrintEvalErrorMessage();
        } // else
          
      } // if()
      else {
        ourScheme.PrintErrorMessage();
      } // else

    } // while()

    System.out.println( "Thanks for using OurScheme!" );

  } // main()

} // class Main