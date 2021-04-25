package model;

public class Episode extends Title{

        Integer seasonNumber;
        Integer episodeNumber;

        public Episode(Integer anID, String anOriginalTitle, Boolean anIsAdultIndicator, Integer aStartYear,
                       Integer anEndYear, Integer aRuntimeMinutesAmount, Integer aSeasonNumber,
                       Integer aEpisodeNumber) throws InvalidDatesError {

            super(anID, anOriginalTitle, anIsAdultIndicator, aStartYear, anEndYear, aRuntimeMinutesAmount);
            this.seasonNumber = aSeasonNumber;
            this.episodeNumber = aEpisodeNumber;
        }

        public Integer getSeasonNumber(){
            return seasonNumber;
        }

        public Integer getEpisodeNumber() {
            return episodeNumber;
        }



}


