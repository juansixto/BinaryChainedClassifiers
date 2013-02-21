package data;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import models.Hotel;
import models.Review;

public class Corpus implements Collection<Hotel>, Iterable<Hotel>{
	
	private Collection<Hotel> hotels;
	
	public Corpus(Collection<Hotel> documents) {
		final Vector<Hotel> tmp = new Vector<Hotel>();
		tmp.addAll(documents);
		this.hotels = Collections.unmodifiableCollection(tmp);
	}
	public void split(int max){
		Collection<Hotel> second = new Vector<Hotel>();
		Iterator<Hotel> it = this.hotels.iterator();
		for(int i = 0; i < max; i++){			
			second.add(it.next());
		}
		this.hotels = second;
		
	}
	public void splitEqual(int max, float limit, String key){
		Collection<Hotel> second = new Vector<Hotel>();
		Iterator<Hotel> it = this.hotels.iterator();
		int half = max/2;
		int good = 0;
		int poor = 0;
		while(it.hasNext() && good < half && poor < half)
		{
			Hotel next = it.next();
			Iterator<Review> it2 = next.getReviews().iterator();
			while(it2.hasNext()){
				Review r = (Review) it2.next();
				if(r.getKeyValue(key)>limit){
					good++;
				} else {
					poor++;
				}
			}
			second.add(next);
		}
		this.hotels = second;
		
	}
	@Override
	public boolean add(Hotel arg0) {
		return this.hotels.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends Hotel> arg0) {
		return this.hotels.addAll(arg0);
	}

	@Override
	public void clear() {
		this.hotels.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return this.hotels.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return this.hotels.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return this.hotels.isEmpty();
	}

	@Override
	public Iterator<Hotel> iterator() {
		return this.hotels.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return this.hotels.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return this.hotels.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return this.hotels.removeAll(arg0);
	}

	@Override
	public int size() {
		return this.hotels.size();
	}

	@Override
	public Object[] toArray() {
		return this.hotels.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return this.hotels.toArray(arg0);
	}
	

	public void print() {
		Iterator<Hotel> i = this.hotels.iterator();
		while(i.hasNext()){
			i.next().print();
		}
		
	}

	public int reviews() {
		int counter = 0;
		Iterator<Hotel> i = this.hotels.iterator();
		while(i.hasNext()){
			counter += i.next().getReviews().size();
		}
		return counter;
	}
	
}