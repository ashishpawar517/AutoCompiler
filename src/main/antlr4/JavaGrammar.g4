grammar JavaGrammar;

options {
  language = Java;
}

@header {
  package src.main.antlr4;
}

@lexer::header {
  package src.main.antlr4;
}

@parser::header {
  package src.main.antlr4;
}

compilationUnit
  : packageDeclaration? importDeclaration* typeDeclaration* EOF
  ;

packageDeclaration
  : 'package' qualifiedName ';'
  ;

importDeclaration
  : 'import' qualifiedName ('.' '*')? ';'
  ;

typeDeclaration
  : classDeclaration
  | interfaceDeclaration
  | enumDeclaration
  | annotationTypeDeclaration
  ;

classDeclaration
  : 'class' Identifier typeParameters? ('extends' type)? ('implements' typeList)? classBody
  ;

interfaceDeclaration
  : 'interface' Identifier typeParameters? ('extends' typeList)? interfaceBody
  ;

enumDeclaration
  : 'enum' Identifier ('implements' typeList)? enumBody
  ;

annotationTypeDeclaration
  : '@' 'interface' Identifier annotationTypeBody
  ;

classBody
  : '{' classBodyDeclaration* '}'
  ;

interfaceBody
  : '{' interfaceBodyDeclaration* '}'
  ;

enumBody
  : '{' enumConstantList? ','? enumBodyDeclarations? '}'
  ;

annotationTypeBody
  : '{' annotationTypeElementDeclaration* '}'
  ;

classBodyDeclaration
  : ';'
  | modifier* memberDeclaration
  | staticInitializer
  | constructorDeclaration
  ;

memberDeclaration
  : methodDeclaration
  | fieldDeclaration
  | classDeclaration
  | interfaceDeclaration
  | enumDeclaration
  | annotationTypeDeclaration
  ;

methodDeclaration
  : methodHeader methodBody
  ;

methodHeader
  : result methodDeclarator throws_?
  ;

methodDeclarator
  : Identifier '(' formalParameterList? ')'
  ;

formalParameterList
  : formalParameter (',' formalParameter)*
  ;

formalParameter
  : variableModifier* type variableDeclaratorId
  ;

variableDeclaratorId
  : Identifier ('[' ']')*
  ;

methodBody
  : block
  ;

result
  : type
  | 'void'
  ;

throws_
  : 'throws' qualifiedNameList
  ;

qualifiedNameList
  : qualifiedName (',' qualifiedName)*
  ;

fieldDeclaration
  : type variableDeclarators ';'
  ;

variableDeclarators
  : variableDeclarator (',' variableDeclarator)*
  ;

variableDeclarator
  : variableDeclaratorId ('=' variableInitializer)?
  ;

variableInitializer
  : expression
  | arrayInitializer
  ;

arrayInitializer
  : '{' variableInitializer (',' variableInitializer)* ','? '}'
  ;

block
  : '{' blockStatement* '}'
  ;

blockStatement
  : localVariableDeclarationStatement
  | statement
  ;

localVariableDeclarationStatement
  : localVariableDeclaration ';'
  ;

localVariableDeclaration
  : variableModifier* type variableDeclarators
  ;

statement
  : block
  | 'if' parExpression statement ('else' statement)?
  | 'for' '(' forControl ')' statement
  | 'while' parExpression statement
  | 'do' statement 'while' parExpression ';'
  | 'try' block (catchClause+ finallyBlock? | finallyBlock)
  | 'switch' parExpression '{' switchBlockStatementGroup* switchLabel* '}'
  | 'synchronized' parExpression block
  | 'return' expression? ';'
  | 'throw' expression ';'
  | 'break' Identifier? ';'
  | 'continue' Identifier? ';'
  | 'assert' expression (':' expression)? ';'
  | ';'
  | statementExpression ';'
  | Identifier ':' statement
  ;

catchClause
  : 'catch' '(' catchType Identifier ')' block
  ;

catchType
  : qualifiedName ('|' qualifiedName)*
  ;

finallyBlock
  : 'finally' block
  ;

switchBlockStatementGroup
  : switchLabel+ blockStatement*
  ;

switchLabel
  : 'case' constantExpression ':'
  | 'default' ':'
  ;

forControl
  : enhancedForControl
  | forInit? ';' expression? ';' forUpdate?
  ;

forInit
  : localVariableDeclaration
  | statementExpressionList
  ;

enhancedForControl
  : variableModifier* type variableDeclaratorId ':' expression
  ;

forUpdate
  : statementExpressionList
  ;

statementExpressionList
  : statementExpression (',' statementExpression)*
  ;

parExpression
  : '(' expression ')'
  ;

expression
  : primary
  | expression bop=('*'|'/'|'%') expression
  | expression bop=('+'|'-') expression
  | expression bop=('<' '<' | '>' '>' '>' | '>' '>') expression
  | expression bop=('<' | '>' | '<=' | '>=') expression
  | expression bop=('==' | '!=') expression
  | expression bop='&' expression
  | expression bop='^' expression
  | expression bop='|' expression
  | expression bop='&&' expression
  | expression bop='||' expression
  | expression '?' expression ':' expression
  | expression bop=('=' | '+=' | '-=' | '*=' | '/=' | '&=' | '|=' | '^=' | '%=' | '<<=' | '>>=' | '>>>=' ) expression
  ;

primary
  : parExpression
  | 'this'
  | 'super'
  | literal
  | 'new' creator
  | qualifiedName
  | 'void' '.' 'class'
  | nonWildcardTypeArguments (explicitGenericInvocationSuffix | 'this' arguments)
  | explicitGenericInvocation
  | lambdaExpression
  | methodReference
  | arrayAccess
  | expression '.' 'class'
  | expression '.' 'this'
  | expression '.' 'super' arguments
  | expression '.' 'new' innerCreator
  | expression '.' Identifier arguments?
  | primaryNoNewArray_lfno_primary
  ;

primaryNoNewArray_lfno_primary
  : literal
  | 'this'
  | 'super'
  | 'new' creator
  | qualifiedName
  | 'void' '.' 'class'
  | nonWildcardTypeArguments (explicitGenericInvocationSuffix | 'this' arguments)
  | explicitGenericInvocation
  | lambdaExpression
  | methodReference
  | arrayAccess
  | expression '.' 'class'
  | expression '.' 'this'
  | expression '.' 'super' arguments
  | expression '.' 'new' innerCreator
  | expression '.' Identifier arguments?
  ;

literal
  : IntegerLiteral
  | FloatingPointLiteral
  | BooleanLiteral
  | CharacterLiteral
  | StringLiteral
  | NullLiteral
  ;

IntegerLiteral
  : DecimalIntegerLiteral
  | HexIntegerLiteral
  | OctalIntegerLiteral
  | BinaryIntegerLiteral
  ;

FloatingPointLiteral
  : DecimalFloatingPointLiteral
  | HexadecimalFloatingPointLiteral
  ;

BooleanLiteral
  : 'true'
  | 'false'
  ;

CharacterLiteral
  : '\'' SingleCharacter '\''
  ;

StringLiteral
  : '"' StringCharacter* '"'
  ;

NullLiteral
  : 'null'
  ;

DecimalIntegerLiteral
  : DecimalNumeral IntegerTypeSuffix?
  ;

HexIntegerLiteral
  : HexNumeral IntegerTypeSuffix?
  ;

OctalIntegerLiteral
  : OctalNumeral IntegerTypeSuffix?
  ;

BinaryIntegerLiteral
  : BinaryNumeral IntegerTypeSuffix?
  ;

DecimalFloatingPointLiteral
  : DecimalNumeral '.' Digits? ExponentPart? FloatTypeSuffix?
  | '.' Digits ExponentPart? FloatTypeSuffix?
  | DecimalNumeral ExponentPart FloatTypeSuffix?
  | DecimalNumeral ExponentPart? FloatTypeSuffix
  ;

HexadecimalFloatingPointLiteral
  : HexSignificand BinaryExponent FloatTypeSuffix?
  ;

DecimalNumeral
  : '0'
  | NonZeroDigit Digits?
  ;

Digits
  : Digit+
  ;

Digit
  : '0'..'9'
  ;

NonZeroDigit
  : '1'..'9'
  ;

IntegerTypeSuffix
  : 'l'
  | 'L'
  ;

HexNumeral
  : '0' ('x' | 'X') HexDigits
  ;

HexDigits
  : HexDigit+
  ;

HexDigit
  : '0'..'9'
  | 'a'..'f'
  | 'A'..'F'
  ;

OctalNumeral
  : '0' OctalDigits
  ;

OctalDigits
  : OctalDigit+
  ;

OctalDigit
  : '0'..'7'
  ;

BinaryNumeral
  : '0' ('b' | 'B') BinaryDigits
  ;

BinaryDigits
  : BinaryDigit+
  ;

BinaryDigit
  : '0' | '1'
  ;

ExponentPart
  : ('e' | 'E') ('+' | '-')? Digits
  ;

FloatTypeSuffix
  : 'f'
  | 'F'
  | 'd'
  | 'D'
  ;

HexSignificand
  : HexNumeral ('.' HexDigits?)?
  | '.' HexDigits
  ;

BinaryExponent
  : ('p' | 'P') ('+' | '-')? Digits
  ;

type
  : classOrInterfaceType
  | primitiveType
  ;

classOrInterfaceType
  : Identifier typeArguments? ('.' Identifier typeArguments?)*
  ;

primitiveType
  : 'boolean'
  | 'char'
  | 'byte'
  | 'short'
  | 'int'
  | 'long'
  | 'float'
  | 'double'
  ;

typeArguments
  : '<' typeArgument (',' typeArgument)* '>'
  ;

typeArgument
  : type
  | '?' (wildcardBounds)?
  ;

wildcardBounds
  : 'extends' type
  | 'super' type
  ;

qualifiedName
  : Identifier ('.' Identifier)*
  ;

nonWildcardTypeArguments
  : '<' typeList '>'
  ;

typeList
  : type (',' type)*
  ;

creator
  : nonWildcardTypeArguments? createdName classCreatorRest
  | arrayCreatorRest
  ;

createdName
  : Identifier typeArguments?
  | primitiveType
  ;

classCreatorRest
  : arguments classBody?
  ;

arrayCreatorRest
  : '[' ']' ('[' ']')* arrayInitializer?
  ;

innerCreator
  : Identifier typeArguments? classCreatorRest
  ;

arguments
  : '(' expressionList? ')'
  ;

expressionList
  : expression (',' expression)*
  ;

explicitGenericInvocation
  : nonWildcardTypeArguments explicitGenericInvocationSuffix
  ;

explicitGenericInvocationSuffix
  : 'super' arguments
  | Identifier arguments
  ;

lambdaExpression
  : lambdaParameters '->' lambdaBody
  ;

lambdaParameters
  : Identifier
  | '(' formalParameterList? ')'
  | '(' inferredFormalParameterList ')'
  ;

inferredFormalParameterList
  : Identifier (',' Identifier)*
  ;

lambdaBody
  : expression
  | block
  ;

methodReference
  : expression '::' Identifier
  | 'super' '::' Identifier
  | 'new' '::' Identifier
  | type '::' 'new'
  ;

arrayAccess
  : expression '[' expression ']'
  | primaryNoNewArray_lfno_primary '[' expression ']'
  ;
