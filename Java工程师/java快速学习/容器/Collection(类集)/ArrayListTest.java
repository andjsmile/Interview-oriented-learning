import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jdk.internal.access.SharedSecrets;
import jdk.internal.util.ArraysSupport;

import java.util.Collection;
import java.util.ConcurrentModificationException;

/**
 * 动态数组 ArrayList的基本实现
 * 实现最基本的功能
 * 增、删、改、查
 */

public class ArrayListTest<E> extends AbstractList<E> implements Cloneable,List,Serializable{
    // 默认数组大小
    private static final int DEFAULT_CAPACITY=10;

    private static final Object[] EMPTY_ELEMENTDATA={};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA={};

    private int size;

    // transient 默认声明数组不会被序列化
    transient Object[] elementData;

    //protected transient int modCount=0;

    public ArrayListTest(){
        this.elementData=DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public ArrayListTest(int initialCapacity){
        if(initialCapacity>0){
            this.elementData=new Object[initialCapacity];
        }else if(initialCapacity==0){
            this.elementData=EMPTY_ELEMENTDATA;
        }else{
            throw new IllegalArgumentException("illegal capacity:"+initialCapacity);

        }
    }

    public ArrayListTest(Collection<? extends E> c){
        elementData=c.toArray();
        if((size==elementData.length)!=0){
            if(elementData.getClass()!=Object[].class){
                elementData=Arrays.copyOf(elementDat, size,Object[].class);
            }
        }else{
            this.elementData=EMPTY_ELEMENTDATA;
        }

    } 

    public Object[] toArray(){
        return Arrays.copyOf(elementData, size);
    }

    public Object clone(){
        try{
            ArrayList<?>v=(ArrayList<?>) super.clone();
            v.elementData=Arrays.copyOf(elementData, size);
            v.modCount=0;
            return v;
        }catch(CloneNotSupportedException e){
            throw new InternalError(e);
        }
    }

    
    // add 元素
    public boolean add(E e){
        modCount++;
        add(e,elementData,size);
        return true;
    }


    public void add(int index,E element){
        rangeCheckForAdd(index);
        modCount++;
        final int s;
        Object[] elementData;
        if((s=size)==(elementData=this.elementData).length)
            elementData=grow();
        System.arraycopy(elementData, index, elementData, index+1, s-index);
        elementData[index]=element;
        size=s+1;

    }


    public E remove(int index)){
        Object.checkIndex(index,size);
        final Object[] es=elementData;

        @SuppressWarnings("unchecked") E oldValue=(E)es[index];
        fastRemove(es,index);

        return oldValue;

    }

    public void set(){

    }
    public int indexOf(Object ob){
        return indexOfRange(ob,0,size);

    }
    
    public E get(int index){
        Object.checkIndex(index,size);
        return elementData[index];
        

    }

    private void add(E e,Object[] elementData,int s){
        if(s==elementData.length)
            elementData=grow();
        elementData[s]=e;
        size=s+1;
    }

    private Object[] grow(){
        return grow(size+1);
    }

    private Object[] grow(int minCapacity){
        int oldCapacity=elementData.length;
        if(oldCapacity>0 || elementData!=DEFAULTCAPACITY_EMPTY_ELEMENTDATA){
            int newCapacity=ArraysSupport.newLength(oldCapacity,minCapacity-oldCapacity,oldCapacity>>1);
            return elementData=Arrays.copyOf(elementData, newCapacity);
        }else{
            return elementData=new Object[Math.max(DEFAULT_CAPACITY,minCapacity)];
        }
    }

    private void rangeCheckForAdd(int index){
        if(index<0 || index>size){
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index){
        return "Index:"+index+",size:"+size;
    }

    private void fastRemove(Object[] es,int i){
        modCount++;
        final int newSize;
        if((newSize=size-1)>i)
            System.arraycopy(es, i+1, es, i, newSize-i);
        es[size=newSize]=null;
    }

    private int indexOfRange(Object[] ob,int start,int end){
        Object[] es=elementData;
        if(ob==null){
            for(int i=start;i<end;++i){
                if(es[i]==null)
                    return i;
            }
        }else{
            for(int i=start;i<end;++i){
                if(ob.equals(es[i])){
                    return i;
                }
            }
        }
        // 未查找到位置返回-1表示
        return -1;
    }

    // writeObject() 和 readObject() 来控制只序列化数组中有元素填充那部分内容
    private void writeObject(ObjectOutputStream s)throws IOException{
        int expectedModCount=modCount;
        s.defaultWriteObject();

        s.writeInt(size);

        for(int i=0;i<size;++i){
            s.writeObject(elementData[i]);
        }

        if(modCount!=expectedModCount){
            throw new ConcurrentModificationException();
        }
    }

    private void readObject(ObjectInputStream s)throws IOException,ClassNotFoundException{
        s.defaultReadObject();

        // capacity the size
        s.readInt();

        if(size>0){
            SharedSecrets.getJavaObjectInputStreamAccess().checkArray(s,Object[].class,size);
            Object[] elements=new Object[size];

            for(int i=0;i<size;++i){
                elements[i]=s.readObject();
            }
            elementData=elements;
        }else if(size==0){
            elementData=EMPTY_ELEMENTDATA;
        }else{
            throw new InvalidObjectException("invalid size:"+size);
        }
    }
}