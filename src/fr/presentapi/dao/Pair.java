package fr.presentapi.dao;

public class Pair<T, U>{
	private final T _t;
	private final U _u;

	Pair(T t, U u){
		_t = t;
		_u = u;
	}

	public T first(){
		return _t;
	}

	public U second(){
		return _u;
	}
}
