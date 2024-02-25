import random

nomesDePessoas = ["JOSE",
                  "JOAO",
                  "ANTONIO",
                  "FRANCISCO",
                  "CARLOS",
                  "PAULO",
                  "PEDRO",
                  "LUCAS",
                  "LUIZ",
                  "MARCOS",
                  "LUIS",
                  "GABRIEL",
                  "RAFAEL",
                  "DANIEL",
                  "MARCELO",
                  "BRUNO",
                  "EDUARDO",
                  "FELIPE",
                  "RAIMUNDO",
                  "RODRIGO",
                  "MANOEL",
                  "MATEUS",
                  "ANDRE",
                  "FERNANDO",
                  "FABIO",
                  "LEONARDO",
                  "GUSTAVO",
                  "GUILHERME",
                  "LEANDRO",
                  "TIAGO",
                  "ANDERSON",
                  "RICARDO",
                  "MARCIO",
                  "JORGE",
                  "SEBASTIAO",
                  "ALEXANDRE",
                  "ROBERTO",
                  "EDSON",
                  "DIEGO",
                  "VITOR",
                  "SERGIO",
                  "CLAUDIO",
                  "MATHEUS",
                  "THIAGO",
                  "GERALDO",
                  "ADRIANO",
                  "LUCIANO",
                  "JULIO",
                  "RENATO",
                  "ALEX",
                  "VINICIUS",
                  "ROGERIO",
                  "SAMUEL",
                  "RONALDO",
                  "MARIO",
                  "FLAVIO",
                  "DOUGLAS",
                  "IGOR",
                  "DAVI",
                  "MANUEL",
                  "JEFERSON",
                  "CICERO",
                  "VICTOR",
                  "MIGUEL",
                  "ROBSON",
                  "MAURICIO",
                  "DANILO",
                  "HENRIQUE",
                  "CAIO",
                  "REGINALDO",
                  "JOAQUIM",
                  "BENEDITO",
                  "GILBERTO",
                  "MARCO",
                  "ALAN",
                  "NELSON",
                  "CRISTIANO",
                  "ELIAS",
                  "WILSON",
                  "VALDIR",
                  "EMERSON",
                  "LUAN",
                  "DAVID",
                  "RENAN",
                  "SEVERINO",
                  "FABRICIO",
                  "MAURO",
                  "JONAS",
                  "GILMAR",
                  "JEAN",
                  "FABIANO",
                  "WESLEY",
                  "DIOGO",
                  "ADILSON",
                  "JAIR",
                  "ALESSANDRO",
                  "EVERTON",
                  "OSVALDO",
                  "GILSON",
                  "WILLIAN",
                  "JOEL",
                  "SILVIO",
                  "HELIO",
                  "MAICON",
                  "REINALDO",
                  "PABLO",
                  "ARTUR",
                  "VAGNER",
                  "VALTER",
                  "CELSO",
                  "IVAN",
                  "CLEITON",
                  "VANDERLEI",
                  "VICENTE",
                  "ARTHUR",
                  "MILTON",
                  "DOMINGOS",
                  "WAGNER",
                  "SANDRO",
                  "MOISES",
                  "EDILSON",
                  "ADEMIR",
                  "ADAO",
                  "EVANDRO",
                  "CESAR",
                  "VALMIR",
                  "MURILO",
                  "JULIANO",
                  "EDVALDO",
                  "AILTON",
                  "JUNIOR",
                  "BRENO",
                  "NICOLAS",
                  "RUAN",
                  "ALBERTO",
                  "MARIA",
                  "ANA",
                  "FRANCISCA",
                  "ANTONIA",
                  "ADRIANA",
                  "JULIANA",
                  "MARCIA",
                  "FERNANDA",
                  "PATRICIA",
                  "ALINE",
                  "SANDRA",
                  "CAMILA",
                  "AMANDA",
                  "BRUNA",
                  "JESSICA",
                  "LETICIA",
                  "JULIA",
                  "LUCIANA",
                  "VANESSA",
                  "MARIANA",
                  "GABRIELA",
                  "VERA",
                  "VITORIA",
                  "LARISSA",
                  "CLAUDIA",
                  "BEATRIZ",
                  "RITA",
                  "LUANA",
                  "SONIA",
                  "RENATA",
                  "ELIANE",
                  "JOSEFA",
                  "SIMONE",
                  "NATALIA",
                  "CRISTIANE",
                  "CARLA",
                  "DEBORA",
                  "ROSANGELA",
                  "JAQUELINE",
                  "ROSA",
                  "APARECIDA",
                  "DANIELA",
                  "MARLENE",
                  "TEREZINHA",
                  "RAIMUNDA",
                  "ANDREIA",
                  "FABIANA",
                  "LUCIA",
                  "RAQUEL",
                  "ANGELA",
                  "RAFAELA",
                  "JOANA",
                  "LUZIA",
                  "ELAINE",
                  "REGINA",
                  "DANIELE",
                  "SUELI",
                  "DAIANE",
                  "ALESSANDRA",
                  "ISABEL",
                  "SARA",
                  "BIANCA",
                  "FLAVIA",
                  "ERICA",
                  "VIVIANE",
                  "SILVANA",
                  "PRISCILA",
                  "PAULA",
                  "LUIZA",
                  "TEREZA",
                  "ISABELA",
                  "MARTA",
                  "CAROLINE",
                  "JANAINA",
                  "LAURA",
                  "MARLI",
                  "TATIANE",
                  "MARINA",
                  "SILVIA",
                  "MONICA",
                  "TAIS",
                  "MICHELE",
                  "SOLANGE",
                  "EDNA",
                  "FATIMA",
                  "HELENA",
                  "CRISTINA",
                  "ALICE",
                  "CAROLINA",
                  "ROSANA",
                  "ANDRESSA",
                  "CELIA",
                  "VALERIA",
                  "ELIANA",
                  "SABRINA",
                  "ANDREA",
                  "TANIA",
                  "BARBARA",
                  "THAIS",
                  "DENISE",
                  "ROSELI",
                  "GISELE",
                  "MARILENE",
                  "KATIA",
                  "EVA",
                  "MILENA",
                  "EDUARDA",
                  "ELISANGELA",
                  "VILMA",
                  "GEOVANA",
                  "LUCIENE",
                  "TAMIRES",
                  "TATIANA",
                  "ROBERTA",
                  "ELZA",
                  "TALITA",
                  "MARCELA",
                  "PAMELA",
                  "VANIA",
                  "IRENE",
                  "JOSIANE",
                  "LIVIA",
                  "CINTIA",
                  "IVONE",
                  "LAIS",
                  "BENEDITA",
                  "SEBASTIANA",
                  "TAINA",
                  "JOICE"]

nomesDeFamilias = ["Antunes", "Almeida", "Ferreira", "Silva", "Nogueira", "Martins", "Bitencourt", "Dauria",
                   "Machado", "Marioto", "Fukuda", "Onofre", "Gonçalvez", "Jerônimo", "Santo", "Santos",
                   "Cipriano", "Bragança", "Geromel", "Corleone"]

def gerarRenda():
    rendaInteira = random.randint(0, 1500)
    return float(rendaInteira)

def gerarDataDeNacimento():
    return f"'{random.randint(1980, 2024)}-{random.randint(1, 12)}-{random.randint(1, 28)}'"

def obterNomeAleatorio(listaDeNomes):
    return listaDeNomes[random.randint(0,len(listaDeNomes)-1)]

def gerarDados(quantidadeDeFamilias):
    dados = ""
    for i in range(quantidadeDeFamilias):
        dados += f"INSERT INTO Familia(nome) VALUES ('{obterNomeAleatorio(nomesDeFamilias)}');\n"
        dados += f"INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES ({i+1}, '{obterNomeAleatorio(nomesDePessoas)}', '1983-11-02', {gerarRenda()});\n"
        for j in range(random.randint(1,4)):
            dados += f"INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES ({i+1}, '{obterNomeAleatorio(nomesDePessoas)}', {gerarDataDeNacimento()}, {gerarRenda()});\n"
    return dados


def escreverNoArquivo(dados):
    f = open("data.sql", "w")
    f.write(dados)
    f.close()

if __name__ == '__main__':
    quantidadeDeFamilias = int(input('Insira a quantidade de famílias: '))
    escreverNoArquivo(gerarDados(quantidadeDeFamilias))
