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
        open(r'C:\Users\colin\Downloads\imagesProjet\\'+"photo-"+str(ListeSexe[i//2])+"-"+str(ListeAge[i%2])+str(j)+'.png', 'wb').write(r.content)
