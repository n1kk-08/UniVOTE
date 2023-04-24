package com.example.univote.activities;

public class Candidate_data {

    String Candidatename;
    String post;

    public Candidate_data(){

    }
    public Candidate_data(String name, String post){
        this.Candidatename = name;
        this.post = post;

    }

    public String getCandidatename() {
        return Candidatename;
    }

    public void setCandidatename(String Candidatename) {
        this.Candidatename = Candidatename;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String email) {
        this.post = post;
    }

}
