package Stemmer.Model;

public class RootSet
	{
		String lemma;
		String features;
		String originalWord;
		int frequency = 0;

		public RootSet(String lemma, String features, String originalWord)
		{
			this.lemma = lemma;
			this.features 	 	= features;
			this.originalWord	= originalWord;
		}

		public String getLemma() {
			return lemma;
		}

		public String getFeatures() {
			return features;
		}

		public String getOriginalWord() {
			return originalWord;
		}

		public void addFreq()
		{
			this.frequency++;
		}

		public int getFrequency() {
			return frequency;
		}
	}