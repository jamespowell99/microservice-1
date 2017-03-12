package com.powtechconsulting.microservices.resources;

import com.powtechconsulting.microservices.model.Calculation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/calculation")
public class CalculationController {
    private static final String PATTERN = "^-?+\\d+\\.?+\\d*$";


    @RequestMapping("/power")
    public Calculation power(@RequestParam(value = "base") String b, @RequestParam(value = "exponent") String e) {
        List<String> input = Arrays.asList(b, e);
        List<String> output = new ArrayList<>();
        String powValue = "";
        if (b != null && e != null && b.matches(PATTERN) && e.matches(PATTERN)) {
            powValue = String.valueOf(Math.pow(Double.valueOf(b), Double.valueOf(e)));
        } else {
            powValue = "incorrect args";
        }
        output.add(powValue);
        return new Calculation("pow", input, output);
    }

    @RequestMapping(value = "/sqrt/{value:.+}", method = RequestMethod.GET)
    public Calculation sqrt(@PathVariable(value = "value") String aValue) {
        List<String> input = new ArrayList<>();
        input.add(aValue);
        List<String> output = new ArrayList<>();
        String sqrtValue = "";
        if (aValue != null && aValue.matches(PATTERN)) {
            sqrtValue = String.valueOf(Math.sqrt(Double.valueOf(aValue)));
        } else {
            sqrtValue = "Input value is not set to numeric value.";
        }
        output.add(sqrtValue);
        return new Calculation("sqrt", input, output);
    }
}
