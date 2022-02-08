package aed.tries;

import java.util.Arrays;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.tree.GeneralTree;
import es.upm.aedlib.tree.LinkedGeneralTree;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;


public class DictImpl implements Dictionary {
	// A boolean because we need to know if a word ends in a node or not
	GeneralTree<Pair<Character,Boolean>> tree;

	public DictImpl()
	{
		tree = new LinkedGeneralTree<>();
		tree.addRoot(new Pair<Character,Boolean>(null,false));
	}

	/*
	 - Un diccionario siempre tiene un nodo ra´ız especial:
		1.Añadido por el constructor de la clase (no hace falta hacerlo).
		2.Contiene un elemento con carácter null y boolean f.
	 - Un nodo nunca tiene dos hijos con el mismo carácter
	-  Hijos: ordenados alfabéticamente. (Un hijo con carácter “a” aparece antes que un hijo con “b”
	*/


	public void add(String word) 
	{ 	
		//Lanzamos excepcion en caso de que word este vacio o sea nulo.
		if(word ==null || word.isEmpty()) {
			throw new IllegalArgumentException();
		}
		//Creamos un cursor en la raiz.
		Position<Pair<Character,Boolean>> cursor = tree.root();
		//Un for que recorre la longitud de word.
		for(int i = 0; i<word.length(); i++) {
			String arbol =tree.toString();
			System.out.println(arbol);
			//Si hay child con la letra que buscamos:
			if(search(word.charAt(i), cursor) != null) {
				cursor = search(word.charAt(i), cursor);//Avanzamos el cursor.
				//Si llegamos al final de word, y esta letra es un child.
				if(i==word.length()-1) {
					cursor.element().setRight(true);//Cambiar a true el boolean.
				}
			}else {//Si ningun child tiene el caracter que buscamos
				//Creamos la nueva palabra con un boolean falso
				Pair<Character,Boolean> pair = new Pair<Character,Boolean>(word.charAt(i),false);	
				//En caso de ser la ultima letra de word, cambiamos el boolean a true.
				if(i==word.length()-1) {
					pair.setRight(true);	
				}	
				//Llamamos al auxiliar para anadir un child respetando el orden alfabetico.
				addAlphabetic(pair, cursor);
				//Avanzamos el cursor al nuevo child con el caracter que buscamos.
				cursor = search(word.charAt(i), cursor);
				
			} 
		}


	}


	public void delete(String word) 
	{
		if(word == null || word.length()==0) 
			throw new IllegalArgumentException();
		Position<Pair<Character,Boolean>> cursor = findPos(word);
		if(cursor!=null) cursor.element().setRight(false);}

	public boolean isIncluded(String word) { 
		  if(word==null||word.length() ==0) throw new IllegalArgumentException();
		  if(findPos(word)!=null) return findPos(word).element().getRight();
		  return false; }
	  
	public PositionList<String> wordsBeginningWithPrefix(String prefix) 
	{ 
		if(prefix == null) 
		{
			throw new IllegalArgumentException();
		}

		PositionList<String> res = new NodePositionList<>();
		Position<Pair<Character,Boolean>> cursor = null;
		if(prefix.length() == 0) 
		{
			cursor = tree.root();
		}
		else
		{
			cursor = findPos(prefix);
			if(cursor.element().getRight()) 
			{
				res.addLast(prefix);
			}
		}
		
		return wordsBeginningWithPrefix(prefix, cursor, res);
	}


	private PositionList<String> wordsBeginningWithPrefix(String prefix,
			Position<Pair<Character, Boolean>> cursor, 
			PositionList<String> res) 
	{
		String arbol =tree.toString();
		System.out.println(arbol);
		for(Position<Pair<Character,Boolean>> hijo : tree.children(cursor)) 
		{
			if(hijo.element().getRight()) 
			{
				res.addLast(prefix + hijo.element().getLeft());
			}
			res = wordsBeginningWithPrefix(prefix + hijo.element().getLeft(), 
					hijo, res);
		}
		System.out.println(res.toString());
		return res;
	}



	//Auxiliar para buscar un caracter de los hijos de un nodo.
	public  Position<Pair<Character,Boolean>> search (char c, Position<Pair<Character,Boolean>> cursor ){  
		Position<Pair<Character,Boolean>> resultado = null;//Creamos una lista resultado.
		if(tree.isExternal(cursor) == false) {//Comprobamos si tiene hijos.
			for (Position<Pair<Character,Boolean>> child : tree.children(cursor)) {//For-each para recorrer los hijos.
				if(child.element().getLeft().equals(c)) {//Comparamos los caracteres.
					resultado = child;//En caso de ser el caracter que buscamos, resultado sera ese child.
				}
			} 
		}

		return resultado;//Devolvemos el child.

	}

	//Anade un hijo al nodo pos conteniendo el elemento pair,respetando el orden alfabetico de los hijos.
	@SuppressWarnings("unused")

		private Position<Pair<Character,Boolean>> addChildAlphabetically(Pair<Character,Boolean> pair, Position<Pair<Character,Boolean>> pos) {
			Character c = pair.getLeft();
			if(tree.size()==2) tree.addChildFirst(pos, pair);
			//recorro los hermanos de pos
			for(Position<Pair<Character,Boolean>> son: tree.children(pos)) {
				//si son(char)< pair(char) -> insert pos antes que son
				Character hijo= son.element().getLeft();
				if(c.compareTo(hijo)>0) {
					tree.insertSiblingBefore(son, pair);
					return pos;
				}
			}
			return pos;
		}

	//Aux para anadir alfabeticamente.
	public void addAlphabetic( Pair<Character,Boolean> parej, Position<Pair<Character,Boolean>> cursor) {
		Character c = parej.getLeft(); //Obtiene el caracter del elemento que queremos anadir.
		boolean colocado = false;//Comprobante para anadir al final.
		//for-each para recorrer los childs.
		for(Position<Pair<Character,Boolean>>child : tree.children(cursor) ) {  
			if(c<child.element().getLeft() && colocado==false) {//Si c es alfebeticamente menor y colocado falso:
				tree.insertSiblingBefore(child, parej); //Anadir como hermano antes de ese child.
				colocado = true; 	//Se ha colocado, por lo que true.	  
			}
		}
		if(colocado == false) {//En caso de no haberse colocado, anadimos al final (extremo derecho)
			tree.addChildLast(cursor, parej);
		}  
	}

	//		public Position<Pair<Character, Boolean>> search (char a, Position<Pair<Character, Boolean>> pos)
	//		{
	//
	//		}
	//Devuelve el nodo cuyo camino desde la raiz contiene
	//la palabra prefix. Si no existe el metodo devuelve null.
	private Position<Pair<Character,Boolean>> findPos(String prefix) { 
		Position<Pair<Character,Boolean>> cursor= tree.root(); 
		for(int i=0; i<prefix.length()&&cursor!=null; i++) {
			cursor=searchChildLabelledBy(prefix.charAt(i), cursor);
		}
		
		return cursor;
	}

	private Position<Pair<Character,Boolean>> searchChildLabelledBy(char ch, Position<Pair<Character,Boolean>> pos) {
		//comprobamos si existe 
		for(Position<Pair<Character,Boolean>> child: tree.children(pos)) {
			Character son = child.element().getLeft();
			if(son.compareTo(ch)==0) 
				return child;
		  	}
		return null; //si ningun hijo contiene ch , return null
	}
	//Devuelve el hijo del nodo pos que contiene el caracter ch.
	//	private Position<Pair<Character,Boolean>> searchChildLabelledBy(char ch, Position<Pair<Character,Boolean>> pos) {
	//
	//	}
}