import names
import json
global Range, ListeSexe


Range =100
listenom_Fam = [names.get_last_name() for _ in range(Range)]
ListeSexe = ["female", "male"]
ListeAge = ["young-adult", "adult"]
list_prenom_female = [names.get_first_name(gender = ListeSexe[0]) for _ in range(Range)]
list_prenom_male = [names.get_first_name(gender = ListeSexe[1]) for _ in range(Range)]


def full_name(gen):
    Machin = []
    data = { 'Prénom':'',
    'Nom':''}
    gender = ListeSexe
    for i in range(len(listenom_Fam)):
        if gen == gender[0]:
            data['Prénom'] = list_prenom_female[i]
            data['Nom'] = listenom_Fam[i]
        else:
            data['Prénom'] = list_prenom_male[i]
            data['Nom'] = listenom_Fam[i]
    Machin.append(data)
    print(Machin)
full_name("male")
