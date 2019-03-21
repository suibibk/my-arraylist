package cn.myforever.mylist;

import java.util.Arrays;

/**
 * 手写ArrayList 
 * 原理：数组，扩容
 * @author forever
 * @param <E>
 */
public class MyArrayList<E> {
	private int size;//大小
	private int initSize = 10;//初始容量
	private Object[] obj=null;//数组
	public MyArrayList(){
	}
	public MyArrayList(int initSize){
		this.initSize = initSize;
	}
	/**
	 * 添加数组
	 * @param e
	 */
	public Boolean add(E e) {
		//1、初始化以及判断是否需要扩容
		capacity(size+1);
		//2、加入数组,先加入后大小增加1
		obj[size++] = e;
		return true;
	}
	/**
	 * 指定位置添加
	 * @param index
	 * @param e
	 * @return
	 */
	public Boolean add(int index,E e) {
		checkIndex(index);
		//1、初始化以及判断是否需要扩容
		capacity(size+1);
		System.arraycopy(obj, index, obj, index + 1,
                 size - index);
		obj[index] = e;
		size++;
		return true;
	}
	/**
	 * 容器库扩容判断
	 * @param i 需要的容量大小
	 */
	private void capacity(int i) {
		if(obj==null) {
			obj = new Object[initSize];
		}
		//获取现在数组的长度
		int objSize = obj.length;
		//如果需要的数组长度大于现在的长度，就需要扩容1.5倍 Vector是扩容两倍，并且是线程安全带的，加了关键字synchronized
		if(i>objSize) {
			System.out.println("数组不够长啦，需要进行扩容,扩容1.5倍");
			//objSize>>1  除以2
			int newSize  = objSize+(objSize>>1);
			System.out.println("扩容后的大小："+newSize);
			//这里会有个问题，若是newSize==1，那么1+1/2转成整形后还是1，这样就相当于没有扩容，不过若是不指定初始化大小的话就没事
			if(newSize<i) {
				System.out.println("由于初始值为1，扩容后的大小还是为1，所以要用所需大小来当做扩容值");
			   newSize = i;
			}
			//这里进行扩容
			obj = Arrays.copyOf(obj,newSize);
		}
	}
	/**
	 * 获取集合的大小
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 移除
	 */
	public E remove(int index) {
		//判断下标有没有越界
		checkIndex(index);
		//获取该下标的值
		E e  = get(index);
		if(e!=null) {
			int move = size-index-1;
			 System.arraycopy(obj, index+1, obj, index, move);
			 obj[--size]=null;//让GC回收器尽快回收
		}
		return e;
	}
	
	/**
	 * 移除
	 * @param e
	 * @return
	 */
	public Boolean remove(E e) {
		if(e==null) {
			for(int i=0;i<size;i++) {
				if(obj[i]==null) {
					remove(i);
					return true;
				}
			}
		}else {
			for(int i=0;i<size;i++) {
				if(e.equals(obj[i])) {
					remove(i);
					return true;
				}
			}
		}
		return true;
	}
	
	/**
	 * 获取值
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public E get(int index) {
		checkIndex(index);
		E e  = (E) obj[index];
		return e;
	}
	private void checkIndex(int index) {
		if(index>=size||index<0) {
			throw new IndexOutOfBoundsException("下标越界："+index);
		}
	}
	public void clear() {
		for (int i = 0; i < size; i++) {
		       obj[i] = null;
		}
		size = 0;
	}
	public boolean isEmpty() {
	   return size == 0;
	}

	@Override
	public String toString() {
		String str="";
		for(int i=0;i<size;i++) {
			str+=obj[i]+";";
		}
		System.out.println(str);
		return "";
	}
	
}
