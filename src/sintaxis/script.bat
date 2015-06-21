java -cp ..\..\lib\cup.jar java_cup.Main -parser AnalizadorSintacticoImports -symbols ClaseLexica -nopositions lpmImport.cup
java -cp ..\..\lib\cup.jar java_cup.Main -parser AnalizadorSintacticoLPM -symbols ClaseLexica -nopositions lpmAbs.cup
pause