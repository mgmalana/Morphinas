package Stemmer.Model;

public class RootSet
	{
		String word;
		String features;
		String originalWord;
		int frequency = 0;

		public RootSet(String word, String features, String originalWord)
		{
			this.word 		 	= word;
			this.features 	 	= features;
			this.originalWord	= originalWord;
		}

		public String getWord() {
			return word;
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