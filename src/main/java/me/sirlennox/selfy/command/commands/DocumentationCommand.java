/*
 * Copyright (c) 2020 SirLennox & f1nniboy.
 *
 * This code is copyrighted to SirLennox and f1nniboy.
 *
 * Using this code without privileges is not allowed.
 *
 *
 *
 */

package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.documentation.Documentated;
import me.sirlennox.selfy.utils.stat.MathUtils;
import me.sirlennox.selfy.utils.stat.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

public class DocumentationCommand extends Command {
    public DocumentationCommand() {
        super("documentation", "Get all documentations of this project", Category.UTIL);
        this.aliases.add("docs");
    }
    
    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        Reflections reflections = new Reflections("me.sirlennox.selfy");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Documentated.class);
        StringBuilder sb = new StringBuilder();
        for (Class<?> clazz : annotated) {
            Documentated docClass = clazz.getAnnotation(Documentated.class);

            if(docClass != null) {

                Method[] methods = clazz.getMethods();
                Field[] fields = clazz.getFields();
                sb.append("\n\n|-> " + clazz.getName() + " \n» " + docClass.value() + "\n");
                sb.append("\nMethods: \n");

               if(methods.length == 0) sb.append("Nothing to see here.\n");
             for(Method m : methods) {
                 String[] pars = new String[m.getParameterCount()];
                 for (int i = 0; i < pars.length; i++) {
                     pars[i] = m.getParameters()[i].getType().getSimpleName() + " " + m.getParameters()[i].getName();
                 }
                 if (m.getAnnotation(Documentated.class) != null) sb.append("-> " + m.getReturnType().getSimpleName() + " " +  m.getName() + "(" + String.join(", " , pars) + ") \n» " + m.getAnnotation(Documentated.class).value() + "\n\n");
             }

                sb.append("Fields: \n\n");
                if(fields.length == 0) sb.append("Nothing to see here.\n");
                    for (Field m : fields) {
                        if (m.getAnnotation(Documentated.class) != null)
                            sb.append("-> " + m.getType().getSimpleName() + " " + m.getName() + "\n » " + m.getAnnotation(Documentated.class).value() + "\n\n");
                    }


            }
        }
        MessageUtils.editMessage("Documentation" , sb.toString(), MathUtils.getRandomColor().getRGB(), event.getMessage());
    }


}
