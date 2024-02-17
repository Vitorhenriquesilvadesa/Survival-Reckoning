package com.vgames.survivalreckoning.framework.service.event.reactive;

import org.jetbrains.annotations.NotNull;

import java.util.*;

@SuppressWarnings("ALL")
public class ReactiveArrayList<T> extends Reactive<T> implements List<T> {

    private ArrayList<T> elements;

    public ReactiveArrayList() {
        this.elements = new ArrayList<>();
    }

    @Override
    public synchronized void onEvent(ReactiveEvent<T> event) {
        notifyListeners(event);
    }

    @Override
    public synchronized int size() {
        return elements.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public synchronized boolean contains(Object o) {
        return elements.contains(o);
    }

    @NotNull
    @Override
    public synchronized Iterator<T> iterator() {
        return elements.iterator();
    }

    @NotNull
    @Override
    public synchronized Object @NotNull [] toArray() {
        return elements.toArray();
    }

    @NotNull
    @Override
    public synchronized <T1> T1 @NotNull [] toArray(@NotNull T1 @NotNull [] a) {
        return elements.toArray(a);
    }

    @Override
    public synchronized boolean add(T t) {
        elements.add(t);
        notifyListeners(new ReactiveEvent<>(this, elements));
        return true;
    }

    @Override
    public synchronized boolean remove(Object o) {
        Object removedElement = elements.remove(o);
        notifyListeners(new ReactiveEvent<>(this, elements));
        return true;
    }

    @Override
    public synchronized boolean containsAll(@NotNull Collection<?> c) {
        return elements.containsAll(c);
    }

    @Override
    public synchronized boolean addAll(@NotNull Collection<? extends T> c) {
        elements.addAll(c);
        notifyListeners(new ReactiveEvent<>(this, elements));
        return true;
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends T> c) {
        elements.addAll(c);
        notifyListeners(new ReactiveEvent<>(this, elements));
        return true;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        elements.removeAll(c);
        notifyListeners(new ReactiveEvent<>(this, elements));
        return true;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        elements.retainAll(c);
        notifyListeners(new ReactiveEvent<>(this, elements));
        return true;
    }

    @Override
    public void clear() {
        elements.clear();
        notifyListeners(new ReactiveEvent<>(this, elements));
    }

    @Override
    public T get(int index) {
        return elements.get(index);
    }

    @Override
    public T set(int index, T element) {
        elements.set(index, element);
        notifyListeners(new ReactiveEvent<>(this, elements));
        return element;
    }

    @Override
    public void add(int index, T element) {
        elements.add(index, element);
        notifyListeners(new ReactiveEvent<>(this, elements));
    }

    @Override
    public T remove(int index) {
        T element = elements.remove(index);
        notifyListeners(new ReactiveEvent<>(this, elements));
        return element;
    }

    @Override
    public int indexOf(Object o) {
        return elements.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return elements.lastIndexOf(o);
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator() {
        return elements.listIterator();
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator(int index) {
        return elements.listIterator(index);
    }

    @NotNull
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return elements.subList(fromIndex, toIndex);
    }
}
