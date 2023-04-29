import requests
from bs4 import BeautifulSoup

listeLien=['https://generated.photos/faces/natural/young-adult/female','https://generated.photos/faces/natural/adult/female','https://generated.photos/faces/natural/young-adult/male','https://generated.photos/faces/natural/adult/male']
ListeSexe=["female","male"]
ListeAge=["young-adult","adult"]

def ouvrirPageWeb(lien):
    response = requests.get(lien)
    soup = BeautifulSoup ( response.content , "html.parser" )
    soup.prettify()
    table=soup.find_all("img")
    return table

LF=[]
for i in range(4):
    lien=listeLien[i]
    m=ouvrirPageWeb(lien)
    L=[]
    for k in range(1,len(m)):
        mi=str(m[k]).split(" ")[2]
        mi=mi[5:-3]
        L.append(mi)  
    LF.append(L)
    
for i in range(len(LF)):
    for j,url in enumerate(LF[i]):
        r = requests.get(url, allow_redirects=True)
        open(r'C:\Users\colin\Documents\GitHub\projet\projet\images\\'+"photo-"+str(ListeSexe[i//2])+"-"+str(ListeAge[i%2])+str(j)+'.png', 'wb').write(r.content)

"""
public class celldefill extends ListCell<Personne>{
		private final GridPane gridPane = new GridPane(); 
    	private final Label brandLabel = new Label(); 
    	private final Label modelLabel = new Label(); 
    	private final Label descriptionLabel = new Label(); 
    	private final ImageView imagePersonne = new ImageView(); 
    	private final AnchorPane content = new AnchorPane(); 

		public celldefill(){
		imagePersonne.setFitWidth(75); 
        imagePersonne.setPreserveRatio(true); 
        GridPane.setConstraints(imagePersonne, 0, 0, 1, 3); 
        GridPane.setValignment(imagePersonne, VPos.TOP); 
        // 
        modelLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;"); 
        GridPane.setConstraints(modelLabel, 1, 0); 
        // 
        brandLabel.setStyle("-fx-font-size: 0.9em; -fx-font-style: italic; -fx-opacity: 0.5;"); 
        GridPane.setConstraints(brandLabel, 2, 0); 
        // 
        GridPane.setConstraints(descriptionLabel, 1, 1); 
        GridPane.setColumnSpan(descriptionLabel, Integer.MAX_VALUE); 
        //         
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true)); 
        gridPane.setHgap(6); 
        gridPane.setVgap(6); 
        gridPane.getChildren().setAll(imagePersonne, modelLabel, brandLabel, descriptionLabel); 
        AnchorPane.setTopAnchor(gridPane, 0d); 
        AnchorPane.setLeftAnchor(gridPane, 0d); 
        AnchorPane.setBottomAnchor(gridPane, 0d); 
        AnchorPane.setRightAnchor(gridPane, 0d); 
        content.getChildren().add(gridPane); 
    	} 

		@Override 
    	protected void updateItem(Personne perso,boolean bool) { 
        super.updateItem(perso,true); 
        setGraphic(null); 
        setText(null); 
        setContentDisplay(ContentDisplay.LEFT); 
        if (!bool && perso != null) { 
            brandLabel.setText(perso.nom); 
            modelLabel.setText(perso.prenom);
            imagePersonne.setImage(new Image(perso.URLPhoto));
			String[] sex={"homme","femme"};
            descriptionLabel.setText(String.format("sexe: %s, aime: %s", sex[Integer.valueOf(perso.sexe)], sex[Integer.valueOf(perso.attirance)])); 
            setText(null); 
            setGraphic(content); 
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY); 
        } 

	}
"""