package lexico;

import errores.ErrorDispatcher;

%%
%line
%class AnalizadorLexicoLPM
%type  UnidadLexica
%cup
%unicode
%public

%{
  private OperacionesLexico ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new OperacionesLexico(this);
%init}

cero = 0
letra  = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
numeroPositivo = {digitoPositivo}{digito}*
parteDecimal = {digito}* {digitoPositivo}
cadena = \"[^\n\"]*\"
separador = [ \t\r\b\n]
comentario = \\\\[^\n]* 
import = import
if = if
while = while
corcheteApertura = \[
corcheteCierre = \]
array = array
registro = reg
funcion = func
dosPuntos = \:
asignacion = \<\-
flecha = \-\>
punto = \.
true = TT
false = FF
not = not
and = and
or = or
menorIgual = \<\=
menor = \<
mayorIgual = \>\=
mayor = \>
distinto = \!\=
else = else
switch = switch
case = case
default = default
return = return
call = call
int = int
bool = bool
identificador = {letra}({letra}|{digito})*
numeroEntero = ({numeroPositivo}|0)
mas = \+
menos = \-
asterisco = \*
division = /
parentesisApertura = \(
parentesisCierre = \)
igual = \=
coma  = \,

%%
{separador}               	{}
{comentario}              	{}
{numeroPositivo}			{return ops.unidadNumPos();}
{import}					{return ops.unidadImport();}
{cadena}					{return ops.unidadCadena();}
{if}						{return ops.unidadIf();}
{while}						{return ops.unidadWhile();}
{corcheteApertura}			{return ops.unidadCA();}
{corcheteCierre}			{return ops.unidadCC();}
{array}                 	{return ops.unidadArray();}
{registro}					{return ops.unidadRegistro();}
{funcion}					{return ops.unidadFuncion();}
{dosPuntos}					{return ops.unidadDosPuntos();}
{asignacion}				{return ops.unidadAsignacion();}
{flecha}					{return ops.unidadFlecha();}
{punto}						{return ops.unidadPunto();}
{true}						{return ops.unidadTrue();}
{false}						{return ops.unidadFalse();}
{not}						{return ops.unidadNot();}
{and}						{return ops.unidadAnd();}
{or}						{return ops.unidadOr();}
{menorIgual}				{return ops.unidadMenorIgual();}
{menor}						{return ops.unidadMenor();}
{mayorIgual}				{return ops.unidadMayorIgual();}
{mayor}						{return ops.unidadMayor();}
{distinto}					{return ops.unidadDistinto();}
{else}						{return ops.unidadElse();}
{switch}					{return ops.unidadSwitch();}
{case}						{return ops.unidadCase();}
{default}					{return ops.unidadDefault();}
{return}					{return ops.unidadReturn();}
{call}						{return ops.unidadCall();}
{int}						{return ops.unidadInt();}
{bool}						{return ops.unidadBool();}
{identificador}           	{return ops.unidadId();}
{numeroEntero}            	{return ops.unidadEnt();}
{mas}            			{return ops.unidadMas();}
{menos}			  			{return ops.unidadMenos();}
{asterisco}  			  	{return ops.unidadAsterisco();}
{division}        			{return ops.unidadDivision();}
{parentesisApertura}      	{return ops.unidadPAp();}
{parentesisCierre}        	{return ops.unidadPCierre();} 
{igual}                   	{return ops.unidadIgual();} 
{coma}                    	{return ops.unidadComa();}
[^]                       	{ErrorDispatcher.getInstance().sendLexicalError(fila(),lexema());}  