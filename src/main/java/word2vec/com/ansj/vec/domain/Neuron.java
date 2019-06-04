package word2vec.com.ansj.vec.domain;

public abstract class Neuron implements Comparable<Neuron> {
    public int freq;
    public Neuron parent;
    public int code;
    
    public int compareTo(Neuron o) {
        if (this.freq > o.freq) {
            return 1;
        } else {
            return -1;
        }
    }

}
