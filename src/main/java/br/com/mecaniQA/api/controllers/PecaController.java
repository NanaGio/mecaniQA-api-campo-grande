package br.com.mecaniQA.api.controllers;
import br.com.mecaniQA.api.model.Peca;
import br.com.mecaniQA.api.repository.PecaRepository;
import br.com.mecaniQA.api.service.PecaService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import static br.com.mecaniQA.api.repository.PecaRepository.getInstance;

@RestController
@RequestMapping("/api/pecas")
public class PecaController {

    private final PecaService pecaService;

    // Isso aqui conta como injeção de dependência? Acho que não já que não é @Autowired
    public PecaController(PecaService pecaService) {
        this.pecaService = pecaService;
    }
    //GET ALL
    @GetMapping
    public List<Peca> getAllPecas(){
        return pecaService.GetAllPecas();
    }
    // GET BY ID
    @GetMapping("/{id}")
    public Peca GetPecaById(@PathVariable("idPeca") Integer idPeca){
        return pecaService.getById(idPeca);
    }
    //POST
    //PUT
    //DELETE


}

