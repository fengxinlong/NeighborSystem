package com.neighborsystem.util;

import java.util.Arrays;

public class Sort {
	public static void swap(int[] a,int i,int j){
		if(i==j){
			return;
		}
		int t=a[i];
		a[i]=a[j];
		a[j]=t;
	}

	public static void main(String[] args) {
		int[] a={2,4,5,7,2,27};

		int min;
		for(int i=0;i<a.length;i++){
			min=i;
			for(int j=i+1;j<a.length;j++){
				if(a[j]<a[min]){
					min=j;
				}
			}
			swap(a,i,min);
		}
		System.out.println(Arrays.toString(a));
	}
}
