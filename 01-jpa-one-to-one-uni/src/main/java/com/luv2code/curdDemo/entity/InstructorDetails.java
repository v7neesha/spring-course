package com.luv2code.curdDemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;

    @OneToOne(mappedBy = "instructorDetails",cascade =
            {CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE})
    private Instructor instructor;

    public InstructorDetails(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "InstructorDetails{" +
                "youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }

    public InstructorDetails(){}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
    public void setInstructor(Instructor instructor){
        this.instructor = instructor;
    }
    public Instructor getInstructor(){
        return this.instructor;
    }
}
