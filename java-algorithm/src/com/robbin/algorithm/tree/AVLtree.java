package com.robbin.algorithm.tree;

import java.util.Comparator;

/**
 * AVL��ԭ��ʵ�֣�C����ʵ���Լ�Java����ʵ�֣�
 * 
 * 
 * 1. AVL����

	AVL����һ�֡��Ľ����������������������һ����������������ԣ��������ǡ���ǰ��մ�С�����˳����ߴӴ�С��˳�����ģ�
	��ô�����������Ͷ��˻����������ʱ����ң������ɾ����ʱ�䶼��������O(n)��
	������ں������ݶ��ԣ��������޷����ܵġ���ʹ��һ������ȫ��������ݹ���ɵ�������������
	��ͳ�ƽǶ�ȥ�������ڽ������ʴεĲ����ɾ����������������������ĸ߶�Ҳ�����������⡣
	���ʱ���Ҿ�ϣ������һ�ֶ���������������⡣���ʱ��ͳ��֡�ƽ�������������������Ļ���ԭ�����
	���ڲ����ɾ����ʱ�򣬸���������е������Խ��Ͷ������ĸ߶ȡ�ƽ���������������ʹ������AVL���ͺ��������
	
	
  AVL�������κ�һ���ڵ������֧�߶�������֧�߶�֮��ľ���ֵ������1������Ҫ����ע����ǣ�AVL�����岻��˵�Ӹ��ڵ㵽Ҷ�ӽڵ����̾������̾����1��

 * @author robbin.zhang
 * @date 2017/03/16 12:51
 * @see http://www.cnblogs.com/nullzx/p/6075644.html#3559872
 *
 */
public class AVLtree <E>{
	
    private static class Node<E>{
        int h;
        E element;
        Node<E> left;
        Node<E> right;
        //����java�в���C���������ж���ָ��ĸ���������һ����������ã���������д
        Node<E> parent;
         
        public Node(E element, int h, Node<E> left, Node<E> right, Node<E> parent){
            this.element = element;
            this.h = h;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
     
    private Node<E> root;//ָ��α���ڵ������
    private int size = 0;//�ڵ����
    Comparator<? super E> cmp;//�ڵ��С�ıȽ���
     
    //��������˲��������Ĺ��캯������ʹ�ø��ڲ�����Ϊ�Ƚ�����
    //����ʱ����E��Ҫ�̳�Comparable�ӿ�,��������ʱ���׳��쳣
    private static class Cmp<T> implements Comparator<T>{
        @SuppressWarnings({ "unchecked", "rawtypes" })
        @Override
        public int compare(T e1, T e2) {
            return ((Comparable)e1).compareTo(e2);
        }
         
    }
     
    //���Ƚ����Ĺ��캯��
    public AVLtree(Comparator<? super E> cmp){
        if(cmp == null){
            throw new IllegalArgumentException();
        }
        this.cmp = cmp;
        //����һ��α���ڵ㣬�ýڵ������֧����������AVL���ĸ�
        //ʹ��α���ڵ�ڵ��Ŀ���ǣ��Բ����ɾ�������ݹ����ʽ�ܹ�ͳһ
        root = new Node<E>(null, -1, null, null, null);
    }
     
    //�����Ƚ����Ĺ��캯��
    public AVLtree(){
        this.cmp = new Cmp<E>();
        root = new Node<E>(null, -1, null, null, null);
    }
     
    //������нڵ��б䶯���ӵ������𼶵��øú��������Ը��½ڵ�ĸ߶�
    private int getHight(Node<E> x){
        if(x == null){
            return 0;
        }else{
            return x.h;
        }
    }
     
    //��ĳ���ڵ���Ϊ��ʱ������������Сֵ
    private E treeMin(Node<E> x){
        while(x.left != null){
            x = x.left;
        }
        return x.element;
    }
     
    public int size(){
        return size;
    }
     
    //�ȸ�����������ʱʹ��
    public void preorderTraverse(){
        if(root != null){
            preorderTraverse0(root.right);
        }
    }
     
    private void preorderTraverse0(Node<E> x){
        if(x != null){
            System.out.print(x.element+" ");
            if(x.left != null){
                System.out.print(x.left.element+" ");
            }else{
                System.out.print("null  ");
            }
             
            if(x.right != null){
                System.out.print(x.right.element+" ");
            }else{
                System.out.print("null  ");
            }
            System.out.println();
            preorderTraverse0(x.left);
            preorderTraverse0(x.right);
        }
    }
     
    //��ʱ����ת����������������ʾ��ڵ�
    private void antiClockwiseRotate(Node<E> X){
        Node<E> P = X.parent;
        Node<E> XR = X.right;
        if(P.left == X){
            P.left = XR;
        }else{
            P.right = XR;
        }
        XR.parent = P;
         
        X.right = XR.left;
        if(XR.left != null){
            XR.left.parent = X;
        }
         
        XR.left = X;
        X.parent = XR;
         
        //��ת��Ҫ�����������ڵ�ĸ߶�
        X.h = Math.max(getHight(X.left), getHight(X.right)) + 1;
        XR.h = Math.max(getHight(XR.left), getHight(XR.right)) + 1;
    }
     
    //˳ʱ����ת��������,������ʾ��ڵ�
    private void clockwistRotate(Node<E> X){
        Node<E> P = X.parent;
        Node<E> XL = X.left;
        if(P.left == X){
            P.left = XL;
        }else{
            P.right = XL;
        }
        XL.parent = P;
         
        X.left = XL.right;
        if(XL.right != null){
            XL.right.parent = X;
        }
         
        XL.right = X;
        X.parent = XL;
         
        //��ת��Ҫ�����������ڵ�ĸ߶�
        X.h = Math.max(getHight(X.left), getHight(X.right)) + 1;
        XL.h = Math.max(getHight(XL.left), getHight(XL.right)) + 1;
    }
     
    //
    public void insert(E e){
        insert0(root.right, e);
    }
     
    private void insert0(Node<E> x, E e){
        if(x == null){
            root.right = new Node<E>(e, 1, null, null, root);//���ڵ�
            size++;
            return;
        }
         
        if(cmp.compare(e, x.element) > 0){
            if(x.right != null){
                insert0(x.right, e);
                int lh = getHight(x.left);
                int rh = getHight(x.right);
                if(rh - lh == 2){
                    if(cmp.compare(e, x.right.element) > 0){
                        antiClockwiseRotate(x);
                    }else{
                        clockwistRotate(x.right);
                        antiClockwiseRotate(x);
                    }
                }
            }else{
                size++;
                x.right = new Node<E>(e, 1, null, null, x);
            }
        }else
        if(cmp.compare(e, x.element) < 0){
            if(x.left != null){
                insert0(x.left, e);
                int lh = getHight(x.left);
                int rh = getHight(x.right);
                if(lh - rh == 2){
                    if(cmp.compare(e, x.left.element) < 0){
                        clockwistRotate(x);
                    }else{
                        antiClockwiseRotate(x.left);
                        clockwistRotate(x);
                    }
                }
            }else{
                size++;
                x.left = new Node<E>(e, 1, null, null, x);
            }
        }else{
            //Ԫ���Ѵ��ڣ��������µ�Ԫ�ظ��¾ɣ�
            //compare����ֵ����0,������ʾ����������ȫ���
            x.element = e;
        }
        x.h = Math.max(getHight(x.left), getHight(x.right)) + 1;
    }
     
    public boolean delete(E e){
        return delete0(root.right, e);
    }
     
    //����ֵ��ʾ�Ƿ�ɾ���ɹ�
    private boolean delete0(Node<E> x, E e){
        if(x == null){//û���ҵ���ɾ����Ԫ��
            return false;
        }
         
        if(cmp.compare(e, x.element) > 0){
            boolean reval = delete0(x.right, e);
            if(reval == false){
                return false;
            }
             
            int lh = getHight(x.left);
            int rh = getHight(x.right);
            if(lh - rh == 2){
                if(getHight(x.left.left) > getHight(x.left.right)){
                    clockwistRotate(x);
                }else{
                    antiClockwiseRotate(x.left);
                    clockwistRotate(x);
                }
            }
        }else
        if(cmp.compare(e, x.element) < 0){
            boolean reval = delete0(x.left, e);
            if(reval == false){
                return false;
            }
 
            int lh = getHight(x.left);
            int rh = getHight(x.right);
            if(rh - lh == 2){
                if(getHight(x.right.right) > getHight(x.right.left)){
                    antiClockwiseRotate(x);
                }else{
                    clockwistRotate(x.right);
                    antiClockwiseRotate(x);
                }
            }
        }else{//�ҵ���ɾ����Ԫ��
            Node<E> P = x.parent;
             
            if(x.left == null){//����֧Ϊ�գ���ֱ��ɾ��������һ��һ������Ҫ��ת
                size--;
                if(P.left == x){
                    P.left = x.right;
                    if(P.left != null){
                        P.left.parent = P;
                    }
                }else{
                    P.right = x.right;
                    if(P.right != null){
                        P.right.parent = P;
                    }
                }
            }else
            if(x.right == null){//����֧Ϊ�գ���ֱ��ɾ��������һ��һ������Ҫ��ת
                size--;
                if(P.left == x){
                    P.left = x.left;
                    if(P.left != null){                 
                        P.left.parent = P;
                    }
                }else{
                    P.right = x.left;
                    if(P.right != null){
                        P.right.parent = P;
                    }
                }
            }else{//�ҵ���ɾ���Ľڵ�,�ú�̽ڵ���棬Ȼ��ɾ����̽ڵ�
                E nextVal = treeMin(x.right);
                x.element = nextVal;
                delete0(x.right, nextVal);
                int lh = getHight(x.left);
                int rh = getHight(x.right);
                if(lh - rh == 2){
                    if(getHight(x.left.left) > getHight(x.left.right)){
                        clockwistRotate(x);
                    }else{
                        antiClockwiseRotate(x.left);
                        clockwistRotate(x);
                    }
                }
            }
        }
        x.h = Math.max(getHight(x.left), getHight(x.right)) + 1;
        return true;
    }
     
    public static void main(String[] args){
        AVLtree<Integer> avl = new AVLtree<Integer>();
        /*��������Ӳ��룬ɾ���������в���*/
        avl.insert(3);
        avl.insert(5);
        avl.insert(6);
        avl.insert(7);
        avl.insert(8);
        avl.insert(9);
        avl.preorderTraverse();
        System.out.println();
        System.out.println(avl.size());
         
        avl.delete(7);
        avl.delete(8);
        avl.preorderTraverse();
        System.out.println();
        System.out.println(avl.size());
    }
}
