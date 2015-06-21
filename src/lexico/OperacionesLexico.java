package lexico;

import sintaxis.ClaseLexica;
/**
 * Factory of the different lexical units.
 *
 */
public class OperacionesLexico {
  private AnalizadorLexicoLPM analizadorLexico;
  public OperacionesLexico(AnalizadorLexicoLPM alex) {
   this.analizadorLexico = alex;   
  }
  
  public UnidadLexica unidadNumPos(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.NP,analizadorLexico.lexema());
  }
  public UnidadLexica unidadImport(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.IMPORT,"import");
  }
  public UnidadLexica unidadCadena() {
	     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.CADENA,analizadorLexico.lexema());
  }
  public UnidadLexica unidadIf(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.IF,"if");
  }
  public UnidadLexica unidadWhile(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.WHILE,"while");
  }
  public UnidadLexica unidadArray(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.ARRAY,"array");
  }
  public UnidadLexica unidadRegistro(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.REG,"reg");
  }
  public UnidadLexica unidadFuncion(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.FUN,"func");
  }
  public UnidadLexica unidadDosPuntos(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.DP,":");
  }
  public UnidadLexica unidadAsignacion(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.ASIG,"<-");
  }
  public UnidadLexica unidadFlecha(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.FLECHA,"->");
 }
  public UnidadLexica unidadPunto(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.PUNTO,".");
  }
  public UnidadLexica unidadTrue(){
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.TRUE,"TT");
  }
  public UnidadLexica unidadFalse(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.FALSE,"FF");
  }
  public UnidadLexica unidadNot(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.NOT,"not");
  }
  public UnidadLexica unidadAnd(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.AND,"and");
  }
  public UnidadLexica unidadOr(){
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.OR,"or");
  }
  public UnidadLexica unidadMenorIgual(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.MI,"<=");
  }
  public UnidadLexica unidadMenor(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.MENOR,"<");
  }
  public UnidadLexica unidadMayorIgual(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.MAI,">=");
  }
  public UnidadLexica unidadMayor(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.MAYOR,">");
  }
  public UnidadLexica unidadDistinto(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.DIS,"!=");
  }
  public UnidadLexica unidadElse(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.ELSE,"else");
  }
  public UnidadLexica unidadSwitch(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.SWITCH,"switch");
  }
  public UnidadLexica unidadCase(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.CASE,"case");
  }
  public UnidadLexica unidadDefault(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.DEFAULT,"default");
  }
  public UnidadLexica unidadReturn(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.RETURN,"return");
  }
  public UnidadLexica unidadCall(){
	   return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.CALL,"call");
  }
  public UnidadLexica unidadInt(){
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.INT,"int");
  }
  public UnidadLexica unidadBool(){
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.BOOL,"bool");
  }
  public UnidadLexica unidadId() {
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.IDEN,analizadorLexico.lexema()); 
  } 
  public UnidadLexica unidadEnt() {
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.ENT,analizadorLexico.lexema()); 
  } 
  public UnidadLexica unidadMas() {
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.MAS,"+"); 
  } 
  public UnidadLexica unidadMenos() {
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.MENOS,"-"); 
  } 
  public UnidadLexica unidadAsterisco() {
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.POR,"*"); 
  } 
  public UnidadLexica unidadDivision() {
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.DIV,"/"); 
  } 
  public UnidadLexica unidadPAp() {
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.PAP,"("); 
  } 
  public UnidadLexica unidadPCierre() {
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.PCIERRE,")"); 
  } 
  public UnidadLexica unidadCA() {
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.CA,"["); 
  } 
  public UnidadLexica unidadCC() {
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.CC,"]"); 
  } 
  public UnidadLexica unidadIgual() {
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.IGUAL,"="); 
  } 
  public UnidadLexica unidadComa() {
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.COMA,","); 
  } 
  public UnidadLexica unidadEof() {
     return new UnidadLexica(analizadorLexico.fila(),ClaseLexica.EOF,"<EOF>"); 
  }
}
