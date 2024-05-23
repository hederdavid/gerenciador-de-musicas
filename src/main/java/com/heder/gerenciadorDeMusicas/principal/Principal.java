package com.heder.gerenciadorDeMusicas.principal;

import com.heder.gerenciadorDeMusicas.entity.Artista;
import com.heder.gerenciadorDeMusicas.entity.Musica;
import com.heder.gerenciadorDeMusicas.model.DadosArtista;
import com.heder.gerenciadorDeMusicas.model.DadosGeraisArtista;
import com.heder.gerenciadorDeMusicas.model.DadosMusica;
import com.heder.gerenciadorDeMusicas.model.DadosTrack;
import com.heder.gerenciadorDeMusicas.repository.ArtistaRepository;
import com.heder.gerenciadorDeMusicas.repository.MusicaRepository;
import com.heder.gerenciadorDeMusicas.services.ConsumoApi;
import com.heder.gerenciadorDeMusicas.services.ConverteDados;

import java.util.Scanner;

public class Principal {
    private final Scanner SCANNER = new Scanner(System.in);
    private final ConsumoApi CONSUMO_API = new ConsumoApi();
    private final ConverteDados CONVERTE_DADOS = new ConverteDados();
    private ArtistaRepository artistaRepository;
    private MusicaRepository musicaRepository;
    private final String ENDERECO = "http://ws.audioscrobbler.com/2.0/?method=";
    private final String API_KEY = "&api_key=" + System.getenv("API_KEY_LASTFM");
    //http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=ad37f9202884b3a1464e5c1819e6b476&track=Shape+of+You&artist=Ed+Sheeran&format=json
    public Principal(ArtistaRepository artistaRepository, MusicaRepository musicaRepository) {
        this.artistaRepository = artistaRepository;
        this.musicaRepository = musicaRepository;
    }
    public void exibirMenu() {
        boolean isOpcaoValida = false;



        int opcaoEscolhida = -1;
        do {
            System.out.println("\n╔════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                           GERENCIADOR DE MÚSICAS                           ║");
            System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║     1.  Cadastrar artistas                                                 ║");
            System.out.println("║     2.  Cadastrar músicas                                                  ║");
            System.out.println("║     3.  Listar músicas                                                     ║");
            System.out.println("║     4.  Buscar músicas por artistas                                        ║");
            System.out.println("║     5.  Pesquisar dados sobre um artista                                   ║");
            System.out.println("║                                                                            ║");
            System.out.println("║     0.  Encerrar aplicação                                                 ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");

            while (!isOpcaoValida) {
                System.out.print("Opção escolhida: ");
                if (SCANNER.hasNextInt()) {
                    opcaoEscolhida = SCANNER.nextInt();
                    if (opcaoEscolhida >= 0 && opcaoEscolhida <= 5) {
                        isOpcaoValida = true;
                    } else {
                        System.out.println("Opção inválida. Por favor, insira um número entre 0 e 5.");
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                    SCANNER.next();
                }
            }

            isOpcaoValida = false;

            SCANNER.nextLine();

            switch (opcaoEscolhida) {
                case 1 -> buscarArtistaAPI();
                case 2 -> buscarMusicaAPI();
            }
        } while (opcaoEscolhida != 0);

    }

    private void buscarArtistaAPI() {
        System.out.print("Digite o nome do artista: ");
        var nomeArtista = "&artist=" + SCANNER.nextLine().replace(" ", "+").trim();
        var json = CONSUMO_API.obterDados(ENDERECO + "artist.getinfo" + nomeArtista + API_KEY + "&format=json");
        System.out.println(json);
        //http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=Metallica&api_key=ad37f9202884b3a1464e5c1819e6b476&format=json
        DadosGeraisArtista dadosGeraisArtista = CONVERTE_DADOS.obterDados(json, DadosGeraisArtista.class);
        DadosArtista dadosArtista = dadosGeraisArtista.dadosArtista();
        Artista artista = new Artista(dadosArtista);
        artistaRepository.save(artista);

    }

    private void buscarMusicaAPI() {
        System.out.print("Digite o nome da música: ");
        var nomeMusica = "&track=" + SCANNER.nextLine().replace(" ", "+").trim();
        System.out.print("Digite o nome do artista: ");
        var nomeArtista = "&artist=" + SCANNER.nextLine().replace(" ", "+").trim();
        var json = CONSUMO_API.obterDados(ENDERECO + "track.getInfo" + API_KEY + nomeArtista + nomeMusica + "&format=json");
        System.out.println(json);
        //http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=ad37f9202884b3a1464e5c1819e6b476&track=Shape+of+You&artist=Ed+Sheeran&format=json
        DadosTrack dadosTrack = CONVERTE_DADOS.obterDados(json, DadosTrack.class);
        DadosMusica dadosMusica = dadosTrack.track();
        Musica musica = new Musica(dadosMusica);
        musicaRepository.save(musica);

    }
}
