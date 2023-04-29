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

r = requests.get('https://images.generated.photos/fya8frI2pUse_U_Wb7TWMebvYJ9Nk0--NRR6IOO2t6A/rs:fit:256:256/czM6Ly9pY29uczgu/Z3Bob3Rvcy1wcm9k/LnBob3Rvcy92M18w/MDMxMzY3LmpwZw.jpg', allow_redirects=True)
open(r'C:\Users\colin\Documents\GitHub\projet\projet\images\\photo-female-young-adult15.png', 'wb').write(r.content)

