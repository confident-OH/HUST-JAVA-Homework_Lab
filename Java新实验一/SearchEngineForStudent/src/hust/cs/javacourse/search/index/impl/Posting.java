package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractPosting;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Posting extends AbstractPosting {
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public int getDocId() {
        return 0;
    }

    @Override
    public void setDocId(int docId) {

    }

    @Override
    public int getFreq() {
        return 0;
    }

    @Override
    public void setFreq(int freq) {

    }

    @Override
    public List<Integer> getPositions() {
        return null;
    }

    @Override
    public void setPositions(List<Integer> positions) {

    }

    @Override
    public int compareTo(AbstractPosting o) {
        return 0;
    }

    @Override
    public void sort() {

    }

    @Override
    public void writeObject(ObjectOutputStream out) {

    }

    @Override
    public void readObject(ObjectInputStream in) {

    }
}
