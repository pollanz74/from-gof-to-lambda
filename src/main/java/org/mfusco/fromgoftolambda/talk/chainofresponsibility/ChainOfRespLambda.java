package org.mfusco.fromgoftolambda.talk.chainofresponsibility;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class ChainOfRespLambda {

    public static Optional<String> parseTest(File file) {
        if (file.getType() == File.Type.TEXT) {
            return Optional.of("Text file: " + file.getContent());
        } else {
            return Optional.empty();
        }
    }

    public static Optional<String> parsePresentation(File file) {
        if (file.getType() == File.Type.PRESENTATION) {
            return Optional.of("Presentation file: " + file.getContent());
        } else {
            return Optional.empty();
        }
    }

    public static Optional<String> parseAudio(File file) {
        if (file.getType() == File.Type.AUDIO) {
            return Optional.of("Audio file: " + file.getContent());
        } else {
            return Optional.empty();
        }
    }

    public static Optional<String> parseVideo(File file) {
        if (file.getType() == File.Type.VIDEO) {
            return Optional.of("Video file: " + file.getContent());
        } else {
            return Optional.empty();
        }
    }


    public static void main(String[] args) {
        File file = new File(File.Type.AUDIO, "Dream Theater  - The Astonishing");

        System.out.println(parseTest(file)
                .orElse(parsePresentation(file)
                        .orElse(parseAudio(file)
                                .orElse(parseVideo(file)
                                        .orElse("Unknown file")))));

        Function<File, Optional<String>> func = ChainOfRespLambda::parseAudio;

        System.out.println(
                Stream.<Function<File, Optional<String>>>of(ChainOfRespLambda::parseTest,
                        ChainOfRespLambda::parsePresentation,
                        ChainOfRespLambda::parseAudio,
                        ChainOfRespLambda::parseVideo)
                        .map(f -> f.apply(file))
                        .filter(Optional::isPresent)
                        .findFirst()
                        .flatMap(Function.identity())
                        .orElse("Unknown file")
        );
    }
}
