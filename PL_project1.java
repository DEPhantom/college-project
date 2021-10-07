//package PL109_10727220;
// alt+shift+b re compile
// java string 比較一定要用equal
// scanner 讀char是讀整行
// 路徑格式 C:\\Users\\user\\Desktop\\java\\test.txt
import java.lang.Exception;
import java.util.Scanner;
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
    f = new File("C:\\Users\\user\\Desktop\\java\\test3.txt"); // open file
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
      if( temp == '\"' ) {
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
        } // while()                
        
        if ( mtoken.equals( "\"" ) == true ) { // next " has been use
          mnext_token = '\0'; 
        } // if()
        else {
          mnext_token = temp; 
        } // else
          

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

  Scheme() {
    mhas_error = false;
    mscanner = new Lexial_analysis();
    mexit = "";
    mspace_num = 0;
    mfirst_atom = false;
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
      else {
        ;
      } // else
         
      Prety_print( current.mright, false );

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
                  current.mright.mis_null == true ) { // print (
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

  void PrintErrorMessage() {
    System.out.println( msexp.mtoken.get( 0 ) );
  } // PrintErrorMessage()

} // class Scheme

class Main{
  public static void main( String [] argv ) {
    Read r = new Read( 0 );
    Scheme ourScheme = new Scheme();
    System.out.println( "Welcome to OurScheme!" );
    while ( ourScheme.No_end() ) { 
      System.out.print( "\n" );
      System.out.print( "> " );
      ourScheme.ReadSExp(); 
      if ( ourScheme.No_error() ) {
        ourScheme.PrintSExp();
      } // if()
      else {
        ourScheme.PrintErrorMessage();
      } // else

    } // while()

    System.out.println( "Thanks for using OurScheme!" );

  } // main()

} // class Main