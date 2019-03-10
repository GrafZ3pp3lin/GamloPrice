# Some Example for QuestionLayouts
## QuestionData
QuestionData contains important informations about a Question Component.
For each Question Component is one QuestionData required, that is called "data" and contains the content data.
Optional is e. g. a QuestionData called:
 * "height": percentage height of this component (String)
 * "grow": should this component grow to the size of the stage (Boolean)
 * "autoPlay": only for Video (Boolean)
 * "preserveRatio": for Video and Images (Boolean)
## Components
### Title
```
IQuestionData<Double> height = new QuestionData<>("height", "10");
IQuestionData<String> titleData = new QuestionData<>("data", "Das ist der Titel");
IQuestionComponent title = new QuestionComponent("Title", Arrays.asList(titleData, height));
```
### Image
```
IQuestionData<String> imageData = new QuestionData<>("data");
imageData.setData(new File("%PATH_TO_IMAGE%").toURI().toString());

IQuestionData<Boolean> imageRatio = new QuestionData<>("preserveRatio", false);

IQuestionComponent image = new QuestionComponent("Image", Arrays.asList(imageData, imageRatio));
```
### Video
```
IQuestionData<String> videoData = new QuestionData<>("data");
videoData.setData(new File("%PATH_TO_VIDEO%").toURI().toString());

IQuestionData<Boolean> videoRatio = new QuestionData<>("preserveRatio", true);
IQuestionData<Boolean> autoPlay = new QuestionData<>("autoPlay", true);

IQuestionComponent video = new QuestionComponent("Video", Arrays.asList(videoData, videoRatio, autoPlay));
```
### Text
```
IQuestionData<String> textData = new QuestionData<>("data", "Lorem ipsum dolor sit amet");
IQuestionData<String> width = new QuestionData<>("width", "80%");
IQuestionData<String> height = new QuestionData<>("height", "15");
IQuestionComponent text = new QuestionComponent("Text", Arrays.asList(textData, width, height));
```
### ButtonGrid
```
IQuestionData<String[]> buttonData = new QuestionData<>("data", new String[] {"one", "two", "three", "four", "five", "six"});
IQuestionData<Boolean> grow = new QuestionData<>("grow", true);
IQuestionComponent buttonGrid = new QuestionComponent("ButtonGrid", Arrays.asList(buttonData, grow));
```
### ImageGrid
```
IQuestionData<String[]> imageGridData = new QuestionData<>("data", new String[] {"%PATH_TO_IMAGE%", "%PATH_TO_IMAGE%", 
        "%PATH_TO_IMAGE%", "%PATH_TO_IMAGE%", "%PATH_TO_IMAGE%", "%PATH_TO_IMAGE%"});
IQuestionData<Boolean> grow = new QuestionData<>("grow", true);
IQuestionComponent imageGrid = new QuestionComponent("ImageGrid", Arrays.asList(imageGridData, grow));
```
## Layout
```
IQuestionLayout layout = new QuestionLayout(Arrays.asList(title, text, imageGrid));
```
## Question
```
IQuestion question = new Question(layout);
```