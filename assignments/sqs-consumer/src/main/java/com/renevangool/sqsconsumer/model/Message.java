package com.renevangool.sqsconsumer.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Message {
    private String message;
    private String photo;
    private String photoFileName;
    private String contentType;
}
